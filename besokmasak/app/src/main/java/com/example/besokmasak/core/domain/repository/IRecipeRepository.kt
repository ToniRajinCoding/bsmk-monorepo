package com.example.besokmasak.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.besokmasak.core.data.source.remote.Resource
import com.example.besokmasak.core.data.source.remote.response.Recipe

interface IRecipeRepository {
    fun getAllRecipe() : LiveData<Resource<List<Recipe>>>

}