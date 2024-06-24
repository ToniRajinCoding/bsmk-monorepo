package com.example.besokmasak.utils

import com.example.besokmasak.core.data.source.local.entity.RecipeEntity
import com.example.besokmasak.core.data.source.remote.response.Recipe
import com.example.besokmasak.core.data.source.remote.response.RecipeResponse
import com.example.besokmasak.core.domain.model.Recipes

object DataMapper {

    fun mapResponseToDomain(input: List<Recipe>) : List<Recipes> =
        input.map {
        Recipes(
            ingredients = it.ingredients,
            instructions = it.instructions,
            recipe_name = it.recipe_name,
            isFavorited = false
        )
    }
//        val recipeList = ArrayList<RecipeEntity>()
//        input.map {
//            val recipes = RecipeEntity(
//                ingredients = it.ingredients,
//                recipe_name = it.recipe_name,
//                instructions = it.instructions,
//                isFavorited = false
//            )
//            recipeList.add(recipes)
//        }
//        return recipeList




    fun mapEntitiesToDomain(input : List<RecipeEntity>) : List<Recipes> =
        input.map {
            Recipes(
                ingredients = it.ingredients,
                instructions = it.instructions,
                recipe_name = it.recipe_name,
                isFavorited = it.isFavorited
            )
        }


    fun mapDomainToEntity(input : Recipes) = RecipeEntity(
        ingredients =  input.ingredients,
        instructions = input.instructions,
        recipe_name = input.recipe_name,
        isFavorited = input.isFavorited
    )

}