package com.example.besokmasak.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.besokmasak.core.data.source.remote.Resource
import com.example.besokmasak.core.data.source.remote.response.Recipe

interface RecipeUseCase {
    fun getAllRecipe(): LiveData<Resource<List<Recipe>>>
}