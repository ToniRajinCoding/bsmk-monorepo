package com.example.besokmasak

import com.example.besokmasak.core.data.source.RecipeRepository
import com.example.besokmasak.core.data.source.local.LocalDataSource
import com.example.besokmasak.core.data.source.remote.RemoteDataSource
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.utils.AppExecutors
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RecipeRepositoryTest {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var appExecutors: AppExecutors

    private lateinit var repository: RecipeRepository

    private lateinit var mockListRecipes: List<Recipes>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockListRecipes = listOf(
            Recipes(listOf("dd", "ee"), listOf("bb,cc"), "aa"),
            Recipes(listOf("dd", "ee"), listOf("bb,cc"), "aa"),
            Recipes(listOf("dd", "ee"), listOf("bb,cc"), "aa")
        )
        repository = RecipeRepository(remoteDataSource, localDataSource, appExecutors)
    }


    @Test
    fun getAllRecipesFromDB() {

        Mockito.`when`(repository.getAllRecipe()).thenReturn(emptyList<Recipes>())

        val flow = repository.getAllRecipe()

        val result = runBlocking { flow.collect{it} }

        assertEquals(emptyList<Recipes>(), result?.getOrAwaitValue() ?: emptyList())

    }

}