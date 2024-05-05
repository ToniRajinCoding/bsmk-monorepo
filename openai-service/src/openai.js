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
        each object will have these attributes: recipe_name (string), ingredients (array), and instruction (array). 
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

app.post("/get-recipe", async (req, res) => {
  try {
    const { ingredients, method } = req.body;
    const recipe = await getRecipe(ingredients, method);
    res.send(recipe);
  } catch (error) {
    console.error(error);
    res.status(500).send("Error generating recipe");
  }
});

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Server listening on port ${port}`));
