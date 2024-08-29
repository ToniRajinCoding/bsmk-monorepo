package com.example.besokmasak

import com.example.besokmasak.core.data.source.RecipeRepository
import com.example.besokmasak.core.data.source.Resource
import com.example.besokmasak.core.data.source.local.LocalDataSource
import com.example.besokmasak.core.data.source.local.entity.IngredientEntity
import com.example.besokmasak.core.data.source.local.entity.RecipeEntity
import com.example.besokmasak.core.data.source.remote.RemoteDataSource
import com.example.besokmasak.core.data.source.remote.network.ApiResponse
import com.example.besokmasak.core.data.source.remote.request.RecipeRequest
import com.example.besokmasak.core.data.source.remote.response.Ingredients
import com.example.besokmasak.core.data.source.remote.response.Recipe
import com.example.besokmasak.core.data.source.remote.response.RecipeResponse
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.core.domain.model.RecipesIngredients
import com.example.besokmasak.utils.AppExecutors
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
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

    @Mock
    private lateinit var recipeEntity: RecipeEntity

    private lateinit var repository: RecipeRepository

    private lateinit var mockListRecipes: List<RecipeEntity>

    private lateinit var mockRequest : RecipeRequest

    private lateinit var mockRecipe : RecipeResponse

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mockListRecipes = listOf(RecipeEntity("aa", listOf(IngredientEntity("aa", "bb")), listOf("bb,cc"), false))
        repository = RecipeRepository(remoteDataSource, localDataSource, appExecutors)
        mockRecipe = RecipeResponse(listOf(Recipe("aa",listOf(Ingredients("aa", "bb")), listOf("bb,cc"))))
        mockRequest = RecipeRequest("aa", "bb", "cc")
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
    fun test_success_search_query() = runTest {

        Mockito.`when`(remoteDataSource.searchQuery(mockRequest)).thenReturn(flowOf(ApiResponse.Success(mockRecipe.recipes)))
        //Flow<Resource<List<Recipes>>>
        val expectedData = flowOf(listOf(Recipes("aa",listOf(RecipesIngredients("aa", "bb")), listOf("bb,cc"))))
        launch {
            repository.searchRecipe(mockRequest).collectLatest { result ->
                assertThat(result, instanceOf(Resource.Success::class.java))
                assertThat((result as Resource.Success).data, equalTo(expectedData.first()))
            }
        }
    }

    @Test
    fun test_empty_search_query() = runTest {

        Mockito.`when`(remoteDataSource.searchQuery(mockRequest)).thenReturn(flowOf(ApiResponse.Empty))

        repository.searchRecipe(mockRequest).collectLatest { result ->
            assertThat(result, instanceOf(Resource.Loading::class.java))
        }

    }

    @Test
    fun test_error_search_query() = runTest {

        Mockito.`when`(remoteDataSource.searchQuery(mockRequest)).thenReturn(flowOf(ApiResponse.Error("Error!")))

        repository.searchRecipe(mockRequest).collectLatest { result ->
            assertThat(result, instanceOf(Resource.Error::class.java))
            assertThat((result as Resource.Error).message, equalTo("Error!"))
        }
    }

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


    @Test
    fun getAllRecipes_returnEmptyList() = runTest {

        val mockEmptyRecipeEntity : List<RecipeEntity> = emptyList()
        val expectedData : List<Any> = emptyList()

        //Flow<List<RecipeEntity>>
        Mockito.`when`(localDataSource.getFavoritedRecipe()).thenReturn(flowOf(mockEmptyRecipeEntity))

        repository.getAllRecipe().collectLatest { result ->
            assertThat(result, equalTo(expectedData) )
        }

    }

    @Test
    fun updateFavoritedRecipe_favorited() = runTest {



    }

    @Test
    fun updateFavoritedRecipe_unFavorited() = runTest {

    }






}