package com.example.besokmasak.recipedetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.besokmasak.R
import com.example.besokmasak.databinding.RecipeDetailBinding
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.utils.ui.IngredientAdapter
import com.example.besokmasak.utils.ui.InstructionAdapter
import com.example.besokmasak.utils.RecipeDiffCallBack
import com.google.android.material.snackbar.Snackbar

class RecipeResultAdapter(
    private val viewmodel: RecipeResultViewModel
) : RecyclerView.Adapter<RecipeResultAdapter.RecipeResponseViewHolder>() {

    private var listRecipe = ArrayList<Recipes>()

    class RecipeResponseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RecipeDetailBinding.bind(itemView)
        fun bind(data: Recipes, viewmodel: RecipeResultViewModel) {
            with(binding) {
                Log.d("isi dari recipes, recipe name: ", data.recipe_name)
                val instructionAdapter = InstructionAdapter(data.instructions)
                val ingredientAdapter = IngredientAdapter(data.ingredients)
                tvRecipeName.text = data.recipe_name
                rvInstructions.adapter = instructionAdapter
                rvInstructions.layoutManager = LinearLayoutManager(itemView.context)
                rvIngredients.adapter = ingredientAdapter
                rvIngredients.layoutManager = LinearLayoutManager(itemView.context)
                var counter = 0
                favBtn.setOnClickListener {
                    val isFavorited = counter % 2 == 0
                    val message = if (isFavorited) R.string.added_fav else R.string.removed_fav
                    viewmodel.updateFavoriteState(data,isFavorited)
                    val snackbar = Snackbar.make(itemView, message, Snackbar.LENGTH_SHORT)
                    with(snackbar){
                        setBackgroundTint(context.getColor(R.color.darker_teal))
                        setTextColor(context.getColor(R.color.white))
                        snackbar.show()
                    }
                    counter++
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipeResponseViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.recipe_detail, parent, false
        )
    )

    override fun getItemCount(): Int {
        return listRecipe.size
    }

    fun updateRecipeList(newData: List<Recipes>) {
        Log.d("asd", "masuk logging update")
        val old = getRecipeList()
        val new = old.plus(newData)
        val diffCallback = RecipeDiffCallBack(old, new)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        setRecipeList(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setRecipeList(recipeList: List<Recipes>) {
        listRecipe.clear()
        listRecipe.addAll(recipeList)
        notifyDataSetChanged()
    }

    fun generateMoreRecipeList(recipeList: List<Recipes>){
        listRecipe.addAll(recipeList)
        notifyItemRangeInserted(itemCount,recipeList.size)
//        listRecipe.addAll(recipeList)
//        notifyDataSetChanged()
    }

    fun getRecipeList(): List<Recipes> {
        return listRecipe
    }

    override fun onBindViewHolder(holder: RecipeResponseViewHolder, position: Int) {
        val data = listRecipe[position]
        holder.bind(data, viewmodel)
    }
}