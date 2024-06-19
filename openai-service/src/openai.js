import * as dotenv from "dotenv";
import OpenAI from "openai";
import express from "express";
import bodyParser from "body-parser";

const app = express();
const openai = new OpenAI(process.env.OPENAI_API_KEY);
dotenv.config();
app.use(bodyParser.json());

async function getRecipe(ingredients, method) {
  const completion = await openai.chat.completions.create({
    messages: [
      {
        role: "system",
        content: `You are a indonesian house chef. 
        Generate answer with JSON format structured {recipes: [{object}, {object}, {object}, ...]}
        each object will have these attributes: recipe_name (string), ingredients (array), and instructions (array).
        Do not change the JSON attribute name: recipe_name, ingredients, instructions.
        `,
      },
      {
        role: "user",
        content: `Please give me 10 recipes inspiration that can be created from ${ingredients} and I want it to be ${method}`,
      },
    ],
    model: "gpt-3.5-turbo",
  });

  return completion.choices[0].message.content;
}

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

// app.post("/get-recipe", async (req, res) => {
//   try {
//     const { ingredients, method } = req.body;
//     const recipe = await getRecipe(ingredients, method);
//     console.log(recipe);
//     res.send(recipe);
//   } catch (error) {
//     console.error(error);
//     res.status(500).send("Error generating recipe");
//   }
// });

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

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Server listening on port ${port}`));
