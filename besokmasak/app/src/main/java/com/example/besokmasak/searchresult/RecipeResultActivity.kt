package com.example.besokmasak.searchresult

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.besokmasak.core.data.source.Resource
import com.example.besokmasak.databinding.ActivityRecipeResultBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

@AndroidEntryPoint
class RecipeResultActivity : AppCompatActivity(), CardStackListener {

    private lateinit var binding: ActivityRecipeResultBinding

    private val manager by lazy { CardStackLayoutManager(this, this) }
    // private val adapter by lazy { RecipeResultAdapter(collectRecipe(), LayoutInflater.from(this)) }
    private val viewModel : RecipeResultViewModel by viewModels()
    private lateinit var adapter: RecipeResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ingredients = intent.getStringExtra("ingredients")
        val method = intent.getStringExtra("method")
        Log.d("ingredient method logging : ", "$ingredients & $method")

        initialize()

        adapter = RecipeResultAdapter(viewModel)

        //if the intent passed successfully
        if(ingredients != null && method != null){
            viewModel.searchQuery(ingredients,method)
            viewModel.recipesLiveData.observe(this) { resource ->
                when (resource) {
                    is Resource.Success -> {
                        binding.loadingBar.visibility = View.INVISIBLE
                        binding.csvRecipeDetail.visibility = View.VISIBLE
                        val recipes = resource.data!!
                        adapter = RecipeResultAdapter(viewModel)
                        adapter.setRecipeList(recipes)
                        binding.csvRecipeDetail.adapter = adapter
                    }
                    is Resource.Loading -> {
                        //show loading indicator
                        binding.loadingBar.visibility = View.VISIBLE
                        binding.csvRecipeDetail.visibility = View.INVISIBLE
                    }
                    is Resource.Error -> {
                        Log.e("Error dalam resource", resource.message ?: "error dalam resource")
                    }
                }
            }
        }


    }

    private fun initialize() {

        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(false)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        binding.csvRecipeDetail.layoutManager = manager

        binding.csvRecipeDetail.itemAnimator.apply {
            if (this is DefaultItemAnimator){
                supportsChangeAnimations = false
            }
        }
    }

    private fun paginate(){
        val scope = CoroutineScope(Dispatchers.IO)

        val ingredients = intent.getStringExtra("ingredients")
        val method = intent.getStringExtra("method")

        if (ingredients != null && method != null){

            Log.d("Paginate", "Paginate DI jalankan")

            scope.launch {
                viewModel.searchQuery(ingredients,method)
                withContext(Dispatchers.Main){
                    viewModel.recipesLiveData.observe(this@RecipeResultActivity) {resource ->
                        when(resource){
                            is Resource.Success -> {
                                Log.d("PAGINATE", "PAGINATE SUCCESS")
                                binding.csvRecipeDetail.post {
                                    adapter.generateRecipeList(resource.data!!)
                                }
                            }
                            is Resource.Loading -> {
                                Log.d("PAGINATE", "PAGINATE LOADING")
                            }
                            is Resource.Error -> {
                                Log.d("error generating new recipes PAGINATE", resource.message ?: "error")
                            }
                        }
                    }
                }
            }

        }
    }


    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: Card Dragged!")
        if (manager.topPosition == (adapter.itemCount - 3) ) {
            paginate()
        }
    }

    override fun onCardSwiped(direction: Direction?) {
        Log.d("CardStackView", "onCardSwiped: Card Swiped!")
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View?, position: Int) {
        Log.d("CardStackView", "onCardAppeared: Card Appear!")
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.d("CardStackView", "onCardDisappeared: Card Dissapear!")
    }


}