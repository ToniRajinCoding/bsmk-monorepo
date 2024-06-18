package com.example.besokmasak.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.besokmasak.R
import com.example.besokmasak.databinding.RecipeDetailBinding
import com.example.besokmasak.core.data.source.remote.response.Recipe
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.utils.RecipeDiffCallBack

class RecipeResultAdapter(
    private var recipeList: List<Recipes>,
    private val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<RecipeResultAdapter.RecipeResponseViewHolder>() {

    class RecipeResponseViewHolder(val binding: RecipeDetailBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeResponseViewHolder {
        val view = layoutInflater.inflate(R.layout.recipe_detail, parent, false)
        return RecipeResponseViewHolder(RecipeDetailBinding.bind(view))
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    fun updateRecipeList(newData: List<Recipes>){
        val diffCallback = RecipeDiffCallBack(this.recipeList, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setRecipeList(recipeList: List<Recipes>){
        this.recipeList = recipeList
    }

    fun getRecipeList() : List<Recipes> {
        return recipeList
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