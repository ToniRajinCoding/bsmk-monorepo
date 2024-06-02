package com.example.besokmasak.core.data.source.remote.network

import com.example.besokmasak.core.data.source.remote.request.RecipeRequest
import com.example.besokmasak.core.data.source.remote.response.RecipeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/get-recipe")
    fun createQuery(
        @Body requestBody: RecipeRequest
    ) : Call<RecipeResponse>

}