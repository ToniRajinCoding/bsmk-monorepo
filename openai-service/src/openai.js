import * as dotenv from "dotenv";
import OpenAI from "openai";
import express from "express";
import bodyParser from "body-parser";
import admin from "firebase-admin";
import serviceAccount from "../besok-mask-firebase-adminsdk-h5idz-b9c189be17.json" assert { type: "json" };
import { DocumentSnapshot } from "firebase-admin/firestore";

dotenv.config({ override: true, debug: true });
const app = express();
const openai = new OpenAI(process.env.OPENAI_API_KEY);
app.use(bodyParser.json());

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://besok-mask.firebaseio.com",
});

const db = admin.firestore();
const recipeCollection = db.collection("recipes");

function generateDummyRecipe() {
  const recipes = {
    recipes: [
      {
        recipe_name: "Marsh",
        ingredients: [
          "Micronesia",
          "Singapore",
          "Dominican Republic",
          "Korea (North)",
          "El Salvador",
          "San Marino",
          "Egypt",
          "Angola",
          "Pitcairn",
          "Brunei Darussalam",
        ],
        instructions: [
          "Macdougal Street",
          "Sullivan Place",
          "Bristol Street",
          "Railroad Avenue",
          "Ditmas Avenue",
          "Village Court",
          "Fay Court",
          "Tompkins Avenue",
          "Chase Court",
          "Kenmore Terrace",
        ],
      },
      {
        recipe_name: "Anthony",
        ingredients: [
          "Burkina Faso",
          "United Kingdom",
          "Palau",
          "Zaire",
          "Greenland",
          "Tajikistan",
          "Saint Lucia",
          "Viet Nam",
          "Portugal",
          "Guinea-Bissau",
        ],
        instructions: [
          "River Street",
          "Granite Street",
          "Portal Street",
          "High Street",
          "Aitken Place",
          "Aviation Road",
          "Emerald Street",
          "Karweg Place",
          "Boynton Place",
          "Herkimer Place",
        ],
      },
      {
        recipe_name: "Burnett",
        ingredients: [
          "Syria",
          "Madagascar",
          "Tokelau",
          "St. Pierre and Miquelon",
          "Fiji",
          "Bangladesh",
          "Canada",
          "Rwanda",
          "Kuwait",
          "Sierra Leone",
        ],
        instructions: [
          "Madison Place",
          "Provost Street",
          "Navy Walk",
          "Emerson Place",
          "Matthews Place",
          "Willmohr Street",
          "Wolcott Street",
          "Cleveland Street",
          "Lott Street",
          "Fountain Avenue",
        ],
      },
      {
        recipe_name: "Randi",
        ingredients: [
          "Central African Republic",
          "Antigua and Barbuda",
          "Cape Verde",
          "Yemen",
          "Germany",
          "Ireland",
          "Northern Mariana Islands",
          "Trinidad and Tobago",
          "French Southern Territories",
          "Senegal",
        ],
        instructions: [
          "Conselyea Street",
          "Ocean Parkway",
          "Eckford Street",
          "Hill Street",
          "Engert Avenue",
          "Vernon Avenue",
          "Wyckoff Street",
          "Commercial Street",
          "Eastern Parkway",
          "Himrod Street",
        ],
      },
      {
        recipe_name: "Harper",
        ingredients: [
          "Burundi",
          "Solomon Islands",
          "Andorra",
          "Algeria",
          "Wallis and Futuna Islands",
          "Djibouti",
          "Lithuania",
          "Bolivia",
          "Bahamas",
          "Bermuda",
        ],
        instructions: [
          "Jackson Street",
          "Fleet Street",
          "Thornton Street",
          "Taaffe Place",
          "Greenpoint Avenue",
          "Montauk Avenue",
          "Hooper Street",
          "Ruby Street",
          "Taylor Street",
          "Mill Road",
        ],
      },
      {
        recipe_name: "Harris",
        ingredients: [
          "United Arab Emirates",
          "Eritrea",
          "Monaco",
          "Macedonia",
          "Niue",
          "Puerto Rico",
          "Tuvalu",
          "Romania",
          "Botswana",
          "Israel",
        ],
        instructions: [
          "Locust Avenue",
          "Fleet Place",
          "Turnbull Avenue",
          "Girard Street",
          "Lamont Court",
          "Louis Place",
          "Grant Avenue",
          "Monroe Place",
          "Haring Street",
          "Reeve Place",
        ],
      },
      {
        recipe_name: "Pansy",
        ingredients: [
          "Uruguay",
          "Tonga",
          "Papua New Guinea",
          "Finland",
          "Pakistan",
          "Somalia",
          "Sweden",
          "Zambia",
          "Belgium",
          "Maldives",
        ],
        instructions: [
          "Willow Street",
          "Whitney Avenue",
          "Farragut Place",
          "Nostrand Avenue",
          "Oxford Street",
          "Hanover Place",
          "Fulton Street",
          "Varanda Place",
          "Denton Place",
          "Diamond Street",
        ],
      },
      {
        recipe_name: "Kaye",
        ingredients: [
          "US Minor Outlying Islands",
          "Indonesia",
          "Virgin Islands (US)",
          "Liechtenstein",
          "Georgia",
          "Korea (South)",
          "Morocco",
          "Sri Lanka",
          "Congo",
          "Libya",
        ],
        instructions: [
          "Forbell Street",
          "Winthrop Street",
          "Arlington Avenue",
          "Farragut Road",
          "Grove Street",
          "Locust Street",
          "Keap Street",
          "Juliana Place",
          "Strauss Street",
          "Pulaski Street",
        ],
      },
      {
        recipe_name: "Doyle",
        ingredients: [
          "Bouvet Island",
          "Peru",
          "Namibia",
          "Montserrat",
          "Jordan",
          "India",
          "S. Georgia and S. Sandwich Isls.",
          "Virgin Islands (British)",
          "Cambodia",
          "Guadeloupe",
        ],
        instructions: [
          "Schenectady Avenue",
          "Gold Street",
          "Stone Avenue",
          "Putnam Avenue",
          "Dean Street",
          "Bushwick Place",
          "Oceanic Avenue",
          "Wythe Avenue",
          "Matthews Court",
          "Schermerhorn Street",
        ],
      },
      {
        recipe_name: "Lee",
        ingredients: [
          "Kiribati",
          "Gambia",
          "Tunisia",
          "Russian Federation",
          "Croatia (Hrvatska)",
          "Ukraine",
          "Slovenia",
          "Nauru",
          "Benin",
          "Turks and Caicos Islands",
        ],
        instructions: [
          "Claver Place",
          "Linden Boulevard",
          "Llama Court",
          "Jerome Avenue",
          "Bulwer Place",
          "Verona Street",
          "Lake Place",
          "Bergen Court",
          "Keen Court",
          "Bedford Place",
        ],
      },
    ],
  };

  return recipes;
}

async function getRecipe(ingredients, method) {
  const completion = await openai.chat.completions.create({
    messages: [
      {
        role: "system",
        content: `You are a indonesian cuisine chef. 
        Generate answer with JSON format structured {recipes: [{object}, {object}, {object}, ...]}
        each object must have these attributes in order: recipe_name (string), ingredients (array of ingredients and quantity), and instructions (array of instructions).
        Do not change the JSON attribute name: recipe_name, ingredients[{ingredient, quantity}], instructions[].
        `,
      },
      {
        role: "user",
        content: `Please give me 5 recipes inspiration that can be created from ${ingredients} and I want it to be ${method}`,
      },
    ],
    model: "gpt-3.5-turbo",
  });

  return completion.choices[0].message.content;
}

async function getRecipeIndo(ingredients, method) {
  const completion = await openai.chat.completions.create({
    messages: [
      {
        role: "system",
        content: `Kamu adalah chef dengan pengalaman memasak masakan indonesia, baik rumahan ataupun restoran.
        Generate respon jawaban kamu dengan JSON format yang di struktur seperti ini {recipes: [{object}, {object}, {object}, ...]}.
        Setiap object wajib memiliki atribut seperti berikut secara berurutan: recipe_name (String), ingredients (array yang berisi object dari ingredients dan quantity), dan instructions (array yang terdiri dari instructions)
        Jangan merubah nama atribut JSON: recipe_name, ingredients[{ingredient, quantity}], instructions[]
        `,
      },
      {
        role: "user",
        content: `Berikan 5 inspirasi resep dari ${ingredients} dan aku mau metode memasaknya ${method}`,
      },
    ],
    model: "gpt-3.5-turbo",
  });

  return completion.choices[0].message.content;
}

app.post("/get-recipe", async (req, res) => {
  try {
    //console.log(process.env.OPENAI_API_KEY);
    const { ingredients, method, language } = req.body;

    const response = await (language === "en" ? getRecipe : getRecipeIndo)(
      ingredients,
      method
    );

    //if generating error
    if (!response) {
      return res
        .status(500)
        .send("Error generating recipe. Please try again later.");
    }

    //parse the response string from OpenAI
    const JSONResponse = JSON.parse(response);

    //save data to firebase
    for (const recipe of JSONResponse.recipes) {
      const recipeNameWithoutSpaces = recipe.recipe_name.replace(/\s/g, "_");
      const docRef = recipeCollection.doc(recipeNameWithoutSpaces);

      docRef
        .get()
        .then((docSnapshot) => {
          if (docSnapshot.exists) {
            console.log("Recipe Already exists: ", docSnapshot.id);
          } else {
            return docRef.set(recipe);
          }
        })
        .catch((error) => {
          console.error("Error: ", error);
        });
    }

    console.log(response);
    res.send(response);
  } catch (error) {
    console.error(error);
    res.status(500).send("Error generating recipe!!");
  }
});

app.post("/get-recipe-testdup", async (req, res) => {
  try {
    const jsonData = {
      recipes: [
        {
          recipe_name: "Ayam Goreng Kecap Sajian Nasi Putih",
          ingredients: [
            {
              ingredient: "ayam potong (daging ayam sesuai selera)",
              quantity: "500 gram",
            },
            { ingredient: "kecap manis", quantity: "3 sendok makan" },
            { ingredient: "bawang putih halus", quantity: "3 siung" },
            { ingredient: "garam", quantity: "secukupnya" },
            { ingredient: "merica bubuk", quantity: "secukupnya" },
            { ingredient: "minyak goreng", quantity: "secukupnya" },
            { ingredient: "nasi putih hangat", quantity: "secukupnya" },
          ],
          instructions: [
            "Campurkan ayam potong, kecap manis, bawang putih halus, garam, dan merica bubuk. Diamkan selama 30 menit.",
            "Panaskan minyak goreng dalam wajan.",
            "Goreng ayam hingga matang dan berwarna kecoklatan.",
            "Sajikan ayam goreng kecap dengan nasi putih hangat.",
          ],
        },
      ],
    };

    for (const recipe of jsonData.recipes) {
      const recipeNameWithoutSpaces = recipe.recipe_name.replace(/\s/g, "_");
      const recipeDocRef = recipeCollection.doc(recipeNameWithoutSpaces);
      await recipeDocRef.set(recipe).catch((error) => {
        if (error.code === "ALREADY-EXISTS") {
          console.log("Recipe already exists");
        } else {
          console.error("Error: ", error);
        }
      });
    }

    res.send(jsonData);
  } catch (error) {
    console.error(error);
    res.status(500).send("Error generating recipe!!");
  }
});

app.post("/get-dummy-recipe", (req, res) => {
  try {
    const { ingredients, method } = req.body;
    const response = generateDummyRecipe();
    console.log(response);
    res.send(response);
  } catch (error) {
    console.error(error);
    res.status(500).send("Error generating recipe dummy");
  }
});

// async function getRecipeImage() {
//   const response = await openai.images.generate({
//     model: "dall-e-3",
//     prompt: "a white siamese cat",
//     n: 1,
//     size: "1024x1024",
//   });
//   image_url = response.data[0].url;
// }

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Server listening on port ${port}`));
