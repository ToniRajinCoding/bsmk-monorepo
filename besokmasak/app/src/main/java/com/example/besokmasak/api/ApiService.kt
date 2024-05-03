package com.example.besokmasak.api

import com.example.besokmasak.model.request.RecipeRequest
import com.example.besokmasak.model.response.RecipeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/get-recipe")
    fun createQuery(
        @Body requestBody: RecipeRequest
    ) : Call<RecipeResponse>
}