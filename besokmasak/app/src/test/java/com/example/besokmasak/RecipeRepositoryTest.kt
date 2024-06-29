package com.example.besokmasak

import android.util.Log
import androidx.lifecycle.asLiveData
import com.example.besokmasak.core.data.source.RecipeRepository
import com.example.besokmasak.core.data.source.local.LocalDataSource
import com.example.besokmasak.core.data.source.local.entity.RecipeEntity
import com.example.besokmasak.core.data.source.remote.RemoteDataSource
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.utils.AppExecutors
import com.example.besokmasak.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock

class RecipeRepositoryTest {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var appExecutors: AppExecutors

    @Mock
    private lateinit var recipeEntity: RecipeEntity

    private lateinit var repository: RecipeRepository

    private lateinit var mockListRecipes: List<RecipeEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockListRecipes = listOf(RecipeEntity("aa", listOf("dd", "ee"), listOf("bb,cc"), false))
        repository = RecipeRepository(remoteDataSource, localDataSource, appExecutors)
    }


//    @Test
//    fun getAllRecipes_returnEmptyList() {
//        val emptyFlow = emptyFlow<List<Recipes>>()
//        Mockito.`when`(localDataSource.getFavoritedRecipe()).thenReturn( emptyFlow)
//        val flow = repository.getAllRecipe()
//        runBlocking {
//            flow.collect {
//                assertEquals(emptyList<Recipes>(), it)
//            }
//        }
//    }

    @Test
    fun getAllRecipes_returnDummyList() {
        val mockFlow = flowOf(mockListRecipes)

        Mockito.`when`(localDataSource.getFavoritedRecipe()).thenReturn(mockFlow)

        val flow = repository.getAllRecipe()

        runBlocking {
            flow.collect {
                val actualList = it
                assertEquals(1, actualList.size)
                val actualRecipe = actualList[0]
                assertEquals(mockListRecipes[0].ingredients, actualRecipe.ingredients)
                assertEquals(mockListRecipes[0].instructions, actualRecipe.instructions)
                assertEquals(mockListRecipes[0].recipe_name, actualRecipe.recipe_name)
            }
        }
    }



}