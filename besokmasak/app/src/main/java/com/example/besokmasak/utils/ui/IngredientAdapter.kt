package com.example.besokmasak.utils.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.besokmasak.R
import com.example.besokmasak.core.domain.model.RecipesIngredients
import com.example.besokmasak.databinding.RecipeDetailTextListBinding

class IngredientAdapter(private val ingredientList: List<RecipesIngredients>) :
    RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {
    class IngredientViewHolder(val binding: RecipeDetailTextListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val binding =
            RecipeDetailTextListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = ingredientList[position].ingredient
        val quantity = ingredientList[position].quantity
        holder.binding.itemText.text = holder.itemView.context.getString(R.string.ingredients_list, quantity, ingredient)
    }
}