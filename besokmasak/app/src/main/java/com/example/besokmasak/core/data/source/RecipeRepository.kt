package com.example.besokmasak.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.besokmasak.core.data.source.local.LocalDataSource
import com.example.besokmasak.core.data.source.remote.RemoteDataSource
import com.example.besokmasak.core.data.source.remote.network.ApiResponse
import com.example.besokmasak.core.data.source.remote.request.RecipeRequest
import com.example.besokmasak.core.data.source.remote.response.Recipe
import com.example.besokmasak.core.data.source.remote.response.RecipeResponse
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.core.domain.repository.IRecipeRepository
import com.example.besokmasak.utils.AppExecutors
import com.example.besokmasak.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.Dispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRecipeRepository {

    //already injected
//    companion object {
//        @Volatile
//        private var instance: RecipeRepository? = null
//
//        fun getInstance(
//            remoteData: RemoteDataSource,
//            localData: LocalDataSource,
//            appExecutors: AppExecutors
//        ): RecipeRepository =
//            instance ?: synchronized(this) {
//                instance ?: RecipeRepository(remoteData, localData, appExecutors)
//            }
//    }

    override suspend fun searchRecipe(recipeRequest: RecipeRequest): Flow<Resource<List<Recipes>>> {
        return remoteDataSource.searchQuery(recipeRequest).flowOn(Dispatchers.IO).map { apiResponse ->
            when(apiResponse){
                is ApiResponse.Success -> {
                    Resource.Success(DataMapper.mapResponseToDomain(apiResponse.data))
                }
                is ApiResponse.Empty -> {
                    Resource.Loading()
                }
                is ApiResponse.Error -> {
                    Resource.Error(apiResponse.errorMessage)
                }
            }
        }
    }

    override fun getAllRecipe(): Flow<List<Recipes>> {
        return localDataSource.getFavoritedRecipe().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override suspend fun updateFavoritedRecipe(recipe: Recipes, state: Boolean) {
        val recipeEntity = DataMapper.mapDomainToEntity(recipe)
        recipeEntity.isFavorited = state

        //check if its already favorited or not
        if (recipeEntity.isFavorited) {
            //setFavorite
            localDataSource.updateFavoritedRecipe(recipeEntity)
            localDataSource.insertFavoriteRecipe(recipeEntity)
        } else {
            //unSetFavorite = delete from room db
            appExecutors.diskIO().execute { localDataSource.deleteFavoriteRecipe(recipeEntity) }
        }
    }


}