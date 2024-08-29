package com.example.besokmasak.favorite

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.databinding.ActivityFavoriteDetailBinding
import com.example.besokmasak.utils.ui.IngredientAdapter
import com.example.besokmasak.utils.ui.InstructionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteDetailBinding
    private lateinit var ingredientAdapter : IngredientAdapter
    private lateinit var instructionAdapter: InstructionAdapter
    private val viewModel : FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getSerializableExtra("RECIPES") as? Recipes
            if (data != null) {
                ingredientAdapter = IngredientAdapter(data.ingredients)
                instructionAdapter = InstructionAdapter(data.instructions)
                binding.tvRecipeName.text = data.recipe_name

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