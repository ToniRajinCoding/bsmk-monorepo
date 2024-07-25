package com.example.besokmasak.utils

import com.example.besokmasak.core.data.source.local.entity.IngredientEntity
import com.example.besokmasak.core.data.source.local.entity.RecipeEntity
import com.example.besokmasak.core.data.source.remote.response.Recipe
import com.example.besokmasak.core.data.source.remote.response.RecipeResponse
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.core.domain.model.RecipesIngredients

object DataMapper {

    fun mapResponseToDomain(input: List<Recipe>) : List<Recipes> =
        input.map { listOfRecipe ->
        Recipes(
            ingredients = listOfRecipe.ingredients.map { listOfIngredient ->
                RecipesIngredients(
                    listOfIngredient.ingredient,
                    listOfIngredient.quantity
                )
            },
            instructions = listOfRecipe.instructions,
            recipe_name = listOfRecipe.recipe_name,
            isFavorited = false
        )
    }




    fun mapEntitiesToDomain(input : List<RecipeEntity>) : List<Recipes> =
        input.map { listOfRecipe ->
            Recipes(
                ingredients = listOfRecipe.ingredients.map { listOfIngredient ->
                    RecipesIngredients(
                        listOfIngredient.ingredient,
                        listOfIngredient.quantity
                    )
                },
                instructions = listOfRecipe.instructions,
                recipe_name = listOfRecipe.recipe_name,
                isFavorited = false
            )
        }


    fun mapDomainToEntity(input : Recipes) = RecipeEntity(
        ingredients =  input.ingredients.map { ingredientEntity ->
            IngredientEntity(
                ingredient = ingredientEntity.ingredient,
                quantity = ingredientEntity.quantity
            )
        },
        instructions = input.instructions,
        recipe_name = input.recipe_name,
        isFavorited = input.isFavorited
    )

}