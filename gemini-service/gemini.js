import * as dotenv from "dotenv";
import express from "express";
import bodyParser from "body-parser";
import { GoogleGenerativeAI } from "@google/generative-ai";
import admin from "firebase-admin";
import { readFile } from "fs/promises";
const serviceAccount = JSON.parse(
  await readFile("./besok-mask-28eb9fb8fb5f.json", "utf8")
);

dotenv.config();
const app = express();
app.use(bodyParser.json());

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://besok-mask.firebaseio.com",
});

const db = admin.firestore();
const recipeCollection = db.collection("recipes");

// async function generateRecipe(ingredients, method) {
//   const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY);

//   let model = genAI.getGenerativeModel({
//     model: "gemini-1.5-flash",
//     generationConfig: { responseMimeType: "application/json" },
//   });

//   let prompt = `
//   Generate 5 Indonesia Recipe Inspiration using ${ingredients} and I want it to be ${method}. Answer using this JSON Schema:
//   {
//   "type": "object",
//   "properties": {
//     "recipes": {
//       "type": "array",
//       "items": {
//         "type": "object",
//         "properties": {
//           "recipe_name": {
//             "type": "string"
//           },
//           "ingredients": {
//             "type": "array",
//             "items": {
//               "type": "object",
//               "properties": {
//                 "quantity": {
//                   "type": "string"
//                 },
//                 "ingredient": {
//                   "type": "string"
//                 }
//               },
//               "required": [
//                 "quantity",
//                 "ingredient"
//               ]
//             }
//           },
//           "instructions": {
//             "type": "array",
//             "items": {
//               "type": "string"
//             }
//           }
//         },
//         "required": [
//           "recipe_name",
//           "ingredients",
//           "instructions"
//         ]
//       }
//     }
//   },
//   "required": [
//     "recipes"
//   ]
// }
//   `;

//   let result = await model.generateContent(prompt);
//   console.log(result.response.text());

//   return result.response.text();
// }

// async function generateRecipeIndo(ingredients, method) {
//   const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY);

//   let model = genAI.getGenerativeModel({
//     model: "gemini-1.5-flash",
//     generationConfig: { responseMimeType: "application/json" },
//   });

//   let prompt = `
//   You are an Indonesia Cuisine Chef. You will Generate 5 Indonesia Recipe from the ingredients in my fridge: ${ingredients} and I want the cooking method to be: ${method}.
//   The ingredients and cooking method is inputted by user, if the ingredients and cooking method is not consumable, please ignore it.
//   Generate logical recipe that are consumable and generate detailed instruction.
//   Response MUST be in Bahasa Indonesia with this JSON Schema:
//   {
//   "type": "object",
//   "properties": {
//     "recipes": {
//       "type": "array",
//       "items": {
//         "type": "object",
//         "properties": {
//           "recipe_name": {
//             "type": "string"
//           },
//           "ingredients": {
//             "type": "array",
//             "items": {
//               "type": "object",
//               "properties": {
//                 "quantity": {
//                   "type": "string"
//                 },
//                 "ingredient": {
//                   "type": "string"
//                 }
//               },
//               "required": [
//                 "quantity",
//                 "ingredient"
//               ]
//             }
//           },
//           "instructions": {s
//             "type": "array",
//             "items": {
//               "type": "string"
//             }
//           }
//         },
//         "required": [
//           "recipe_name",
//           "ingredients",
//           "instructions"
//         ]
//       }
//     }
//   },
//   "required": [
//     "recipes"
//   ]
// }
//   `;

//   let result = await model.generateContent(prompt);
//   console.log(result.response.text());

//   return result.response.text();
// }

// function generateDummyRecipe() {
//   const recipes = {
//     recipes: [
//       {
//         recipe_name: "Marsh",
//         ingredients: [
//           "Micronesia",
//           "Singapore",
//           "Dominican Republic",
//           "Korea (North)",
//           "El Salvador",
//           "San Marino",
//           "Egypt",
//           "Angola",
//           "Pitcairn",
//           "Brunei Darussalam",
//         ],
//         instructions: [
//           "Macdougal Street",
//           "Sullivan Place",
//           "Bristol Street",
//           "Railroad Avenue",
//           "Ditmas Avenue",
//           "Village Court",
//           "Fay Court",
//           "Tompkins Avenue",
//           "Chase Court",
//           "Kenmore Terrace",
//         ],
//       },
//       {
//         recipe_name: "Anthony",
//         ingredients: [
//           "Burkina Faso",
//           "United Kingdom",
//           "Palau",
//           "Zaire",
//           "Greenland",
//           "Tajikistan",
//           "Saint Lucia",
//           "Viet Nam",
//           "Portugal",
//           "Guinea-Bissau",
//         ],
//         instructions: [
//           "River Street",
//           "Granite Street",
//           "Portal Street",
//           "High Street",
//           "Aitken Place",
//           "Aviation Road",
//           "Emerald Street",
//           "Karweg Place",
//           "Boynton Place",
//           "Herkimer Place",
//         ],
//       },
//       {
//         recipe_name: "Burnett",
//         ingredients: [
//           "Syria",
//           "Madagascar",
//           "Tokelau",
//           "St. Pierre and Miquelon",
//           "Fiji",
//           "Bangladesh",
//           "Canada",
//           "Rwanda",
//           "Kuwait",
//           "Sierra Leone",
//         ],
//         instructions: [
//           "Madison Place",
//           "Provost Street",
//           "Navy Walk",
//           "Emerson Place",
//           "Matthews Place",
//           "Willmohr Street",
//           "Wolcott Street",
//           "Cleveland Street",
//           "Lott Street",
//           "Fountain Avenue",
//         ],
//       },
//       {
//         recipe_name: "Randi",
//         ingredients: [
//           "Central African Republic",
//           "Antigua and Barbuda",
//           "Cape Verde",
//           "Yemen",
//           "Germany",
//           "Ireland",
//           "Northern Mariana Islands",
//           "Trinidad and Tobago",
//           "French Southern Territories",
//           "Senegal",
//         ],
//         instructions: [
//           "Conselyea Street",
//           "Ocean Parkway",
//           "Eckford Street",
//           "Hill Street",
//           "Engert Avenue",
//           "Vernon Avenue",
//           "Wyckoff Street",
//           "Commercial Street",
//           "Eastern Parkway",
//           "Himrod Street",
//         ],
//       },
//       {
//         recipe_name: "Harper",
//         ingredients: [
//           "Burundi",
//           "Solomon Islands",
//           "Andorra",
//           "Algeria",
//           "Wallis and Futuna Islands",
//           "Djibouti",
//           "Lithuania",
//           "Bolivia",
//           "Bahamas",
//           "Bermuda",
//         ],
//         instructions: [
//           "Jackson Street",
//           "Fleet Street",
//           "Thornton Street",
//           "Taaffe Place",
//           "Greenpoint Avenue",
//           "Montauk Avenue",
//           "Hooper Street",
//           "Ruby Street",
//           "Taylor Street",
//           "Mill Road",
//         ],
//       },
//       {
//         recipe_name: "Harris",
//         ingredients: [
//           "United Arab Emirates",
//           "Eritrea",
//           "Monaco",
//           "Macedonia",
//           "Niue",
//           "Puerto Rico",
//           "Tuvalu",
//           "Romania",
//           "Botswana",
//           "Israel",
//         ],
//         instructions: [
//           "Locust Avenue",
//           "Fleet Place",
//           "Turnbull Avenue",
//           "Girard Street",
//           "Lamont Court",
//           "Louis Place",
//           "Grant Avenue",
//           "Monroe Place",
//           "Haring Street",
//           "Reeve Place",
//         ],
//       },
//       {
//         recipe_name: "Pansy",
//         ingredients: [
//           "Uruguay",
//           "Tonga",
//           "Papua New Guinea",
//           "Finland",
//           "Pakistan",
//           "Somalia",
//           "Sweden",
//           "Zambia",
//           "Belgium",
//           "Maldives",
//         ],
//         instructions: [
//           "Willow Street",
//           "Whitney Avenue",
//           "Farragut Place",
//           "Nostrand Avenue",
//           "Oxford Street",
//           "Hanover Place",
//           "Fulton Street",
//           "Varanda Place",
//           "Denton Place",
//           "Diamond Street",
//         ],
//       },
//       {
//         recipe_name: "Kaye",
//         ingredients: [
//           "US Minor Outlying Islands",
//           "Indonesia",
//           "Virgin Islands (US)",
//           "Liechtenstein",
//           "Georgia",
//           "Korea (South)",
//           "Morocco",
//           "Sri Lanka",
//           "Congo",
//           "Libya",
//         ],
//         instructions: [
//           "Forbell Street",
//           "Winthrop Street",
//           "Arlington Avenue",
//           "Farragut Road",
//           "Grove Street",
//           "Locust Street",
//           "Keap Street",
//           "Juliana Place",
//           "Strauss Street",
//           "Pulaski Street",
//         ],
//       },
//       {
//         recipe_name: "Doyle",
//         ingredients: [
//           "Bouvet Island",
//           "Peru",
//           "Namibia",
//           "Montserrat",
//           "Jordan",
//           "India",
//           "S. Georgia and S. Sandwich Isls.",
//           "Virgin Islands (British)",
//           "Cambodia",
//           "Guadeloupe",
//         ],
//         instructions: [
//           "Schenectady Avenue",
//           "Gold Street",
//           "Stone Avenue",
//           "Putnam Avenue",
//           "Dean Street",
//           "Bushwick Place",
//           "Oceanic Avenue",
//           "Wythe Avenue",
//           "Matthews Court",
//           "Schermerhorn Street",
//         ],
//       },
//       {
//         recipe_name: "Lee",
//         ingredients: [
//           "Kiribati",
//           "Gambia",
//           "Tunisia",
//           "Russian Federation",
//           "Croatia (Hrvatska)",
//           "Ukraine",
//           "Slovenia",
//           "Nauru",
//           "Benin",
//           "Turks and Caicos Islands",
//         ],
//         instructions: [
//           "Claver Place",
//           "Linden Boulevard",
//           "Llama Court",
//           "Jerome Avenue",
//           "Bulwer Place",
//           "Verona Street",
//           "Lake Place",
//           "Bergen Court",
//           "Keen Court",
//           "Bedford Place",
//         ],
//       },
//     ],
//   };

//   return recipes;
// }

async function generateRecipeTitle(ingredients, method) {
  const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY);

  let model = genAI.getGenerativeModel({
    model: "gemini-1.5-flash",
    generationConfig: { responseMimeType: "application/json" },
  });

  let prompt = `
  Generate 10 Recipe Inspiration using ${ingredients} and I want it to be ${method}. 
  The ingredients and cooking method is inputted by user, if the ingredients and cooking method is not consumable, please ignore it.
  Generate logical recipe that are consumable and generate recipe title and short one lined recipe description.
  Answer using this JSON Schema:
  {
  "type": "object",
  "properties": {
    "recipe_title": {
      "type": "string"
    },
    "recipe_description": {
      "type": "string"
    }
  },
  "required": [
    "recipe_title",
    "recipe_description"
  ]
}
  `;

  let result = await model.generateContent(prompt);
  console.log(result.response.text());

  return result.response.text();
}

async function generateRecipeTitleIndo(ingredients, method) {
  const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY);

  let model = genAI.getGenerativeModel({
    model: "gemini-1.5-flash",
    generationConfig: { responseMimeType: "application/json" },
  });

  let prompt = `
  Generate 10 Recipe Inspiration using ${ingredients} and I want it to be ${method}. 
  The ingredients and cooking method is inputted by user, if the ingredients and cooking method is not consumable, please ignore it.
  Generate logical recipe that are consumable and generate recipe title and short one lined recipe description.
  Response must be in Bahasa Indonesia using this JSON Schema:
  {
  "type": "object",
  "properties": {
    "recipe_title": {
      "type": "string"
    },
    "recipe_description": {
      "type": "string"
    }
  },
  "required": [
    "recipe_title",
    "recipe_description"
  ]
}
  `;

  let result = await model.generateContent(prompt);
  console.log(result.response.text());

  return result.response.text();
}

async function generateRecipeDetail(recipe, language) {
  const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY);

  let model = genAI.getGenerativeModel({
    model: "gemini-1.5-flash",
    generationConfig: { responseMimeType: "application/json" },
  });

  let prompt = `
  Generate Recipe detail for ${recipe}. 
  if the recipe is not making sense, please ignore it and generate recipe detail for other recipe instead.
  Generate detailed instruction for each step.
  Answer in this JSON Schema: 
  {
    "type": "object",
    "properties": {
        "recipe_name": {
          "type": "string"
        },
        "ingredients": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "quantity": {
                "type": "string"
              },
              "ingredient": {
                "type": "string"
              }
            },
            "required": [
              "quantity",
              "ingredient"
            ]
          }
        },
        "instructions": {
          "type": "array",
          "items": {
            "type": "string"
          }
        }
      ,
      "required": [
        "recipe_name",
        "ingredients",
        "instructions"
      ]
    },
    "required": [
      "recipes"
    ]
  }
  `;

  let result = await model.generateContent(prompt);
  console.log(result.response.text());

  return result.response.text();
}

async function generateRecipeDetailIndo(recipe, language) {
  const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY);

  let model = genAI.getGenerativeModel({
    model: "gemini-1.5-flash",
    generationConfig: { responseMimeType: "application/json" },
  });

  let prompt = `
  Generate Recipe detail for ${recipe}. 
  if the recipe is not making sense, please ignore it and generate recipe detail for other recipe instead.
  Generate detailed instruction for each step.
  Response must be in Bahasa Indonesia and using this JSON Schema: 
  {
    "type": "object",
    "properties": {
        "recipe_name": {
          "type": "string"
        },
        "ingredients": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "quantity": {
                "type": "string"
              },
              "ingredient": {
                "type": "string"
              }
            },
            "required": [
              "quantity",
              "ingredient"
            ]
          }
        },
        "instructions": {
          "type": "array",
          "items": {
            "type": "string"
          }
        }
      ,
      "required": [
        "recipe_name",
        "ingredients",
        "instructions"
      ]
    },
    "required": [
      "recipes"
    ]
  }
  `;

  let result = await model.generateContent(prompt);
  console.log(result.response.text());

  return result.response.text();
}

app.post("/get-recipe-detail", async (req, res) => {
  try {
    const { recipe, language } = req.body;

    const recipes = await (language === "en"
      ? generateRecipeDetail
      : generateRecipeDetailIndo)(recipe, language);

    //if generating error
    if (!recipes) {
      return res
        .status(500)
        .send("Error generating recipe. Didn't get anything");
    }

    // //parse the response string from OpenAI
    // const RecipeJSON = JSON.parse(recipes);

    // //save data to firebase
    // for (const recipe of RecipeJSON.recipes) {
    //   recipe.language = language;

    //   const recipeNameWithoutSpaces = recipe.recipe_name.replace(/\s/g, "_");
    //   const docRef = recipeCollection.doc(recipeNameWithoutSpaces);

    //   docRef
    //     .get()
    //     .then((docSnapshot) => {
    //       if (docSnapshot.exists) {
    //         console.log("Recipe Already exists: ", docSnapshot.id);
    //       } else {
    //         return docRef.set(recipe);
    //       }
    //     })
    //     .catch((error) => {
    //       console.error("Error: ", error);
    //     });
    // }

    console.log(recipes);
    res.send(recipes);
  } catch (error) {
    console.error(error);
    res.status(500).send("Error generating recipe!!");
  }
});

app.post("/get-recipe-title", async (req, res) => {
  try {
    const { ingredients, method, language } = req.body;

    const recipes = await (language === "en"
      ? generateRecipeTitle
      : generateRecipeTitleIndo)(ingredients, method);

    //if generating error
    if (!recipes) {
      return res
        .status(500)
        .send("Error generating recipe. Didn't get anything");
    }

    console.log(recipes);
    res.send(recipes);
  } catch (error) {
    console.error(error);
    res.status(500).send("Error generating recipe!!");
  }
});

// app.post("/get-dummy-recipe", (req, res) => {
//   try {
//     const { ingredients, method } = req.body;
//     const response = generateDummyRecipe();
//     console.log(response);
//     res.send(response);
//   } catch (error) {
//     console.error(error);
//     res.status(500).send("Error generating recipe dummy");
//   }
// });

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Server listening on port ${port}`));
