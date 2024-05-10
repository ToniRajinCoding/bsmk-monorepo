package com.example.besokmasak.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.besokmasak.databinding.RecipeDetailBinding
import com.example.besokmasak.model.response.Recipe

class RecipeResultAdapter(
    private val recipeList: List<Recipe>
) : RecyclerView.Adapter<RecipeResultAdapter.RecipeResponseViewHolder>() {
    class RecipeResponseViewHolder(val binding: RecipeDetailBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeResponseViewHolder {
        val binding =
            RecipeDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeResponseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeResponseViewHolder, position: Int) {
        Log.d("isi dari recipes, recipe name: ", recipeList[position].recipe_name)
        Log.d("isi dari recipes, ingredients ", recipeList[position].ingredients.toString())
        val recipe = recipeList[position]
        val instructionAdapter = InstructionAdapter(recipe.instructions)
        val ingredientAdapter = IngredientAdapter(recipe.ingredients)
        holder.binding.tvTitle.text = recipe.recipe_name
        holder.binding.rvInstructions.adapter = instructionAdapter
        holder.binding.rvInstructions.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.binding.rvIngredients.adapter = ingredientAdapter
        holder.binding.rvIngredients.layoutManager = LinearLayoutManager(holder.itemView.context)
    }
}