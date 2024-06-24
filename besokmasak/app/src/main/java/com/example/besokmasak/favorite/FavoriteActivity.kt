package com.example.besokmasak.favorite

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.databinding.RecipeDetailBinding
import com.example.besokmasak.ui.IngredientAdapter
import com.example.besokmasak.ui.InstructionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : RecipeDetailBinding
    private lateinit var ingredientAdapter : IngredientAdapter
    private lateinit var instructionAdapter: InstructionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra("recipe", Recipes::class.java)

        Log.d("LOGGING", data.toString())

        if (data != null) {
            ingredientAdapter = IngredientAdapter(data.ingredients)
            instructionAdapter = InstructionAdapter(data.instructions)
            binding.tvTitle.text = data.recipe_name

            Log.d("recipe name", data.recipe_name)


            with(binding.rvIngredients){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = ingredientAdapter
            }

            with(binding.rvInstructions){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = instructionAdapter
            }

        }



    }
}