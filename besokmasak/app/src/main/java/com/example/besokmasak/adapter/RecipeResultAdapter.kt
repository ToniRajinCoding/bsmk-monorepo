package com.example.besokmasak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.besokmasak.databinding.RecipeDetailBinding
import com.example.besokmasak.model.response.Recipe

class RecipeResultAdapter(
    private val recipeList: List<Recipe>,
    private val layoutManager: LinearLayoutManager
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
        val recipe = recipeList[position]
        val instructionAdapter = InstructionAdapter(recipe.instructions)
        val ingredientAdapter = IngredientAdapter(recipe.ingredients)
        holder.binding.tvTitle.text = recipe.recipe_name
        holder.binding.rvInstructions.adapter = instructionAdapter
        holder.binding.rvInstructions.layoutManager = layoutManager
        holder.binding.rvIngredients.adapter = ingredientAdapter
        holder.binding.rvIngredients.layoutManager = layoutManager
    }
}