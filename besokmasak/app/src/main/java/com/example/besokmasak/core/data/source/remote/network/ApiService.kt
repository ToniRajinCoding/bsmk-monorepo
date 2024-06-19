package com.example.besokmasak.core.data.source.remote.network

import com.example.besokmasak.core.data.source.remote.request.RecipeRequest
import com.example.besokmasak.core.data.source.remote.response.RecipeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.Flow

interface ApiService {

    @POST("/get-dummy-recipe")
    suspend fun createQuery(
        @Body requestBody: RecipeRequest
    ) : RecipeResponse

}