package com.example.besokmasak.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.besokmasak.R
import com.example.besokmasak.adapter.RecipeResultAdapter
import com.example.besokmasak.databinding.ActivityRecipeResultBinding
import com.example.besokmasak.model.response.Recipe
import com.example.besokmasak.model.response.RecipeResponse
import com.google.gson.Gson
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod

class RecipeResultActivity : AppCompatActivity(), CardStackListener {

    private lateinit var binding: ActivityRecipeResultBinding

    private val manager by lazy { CardStackLayoutManager(this, this) }
    private val adapter by lazy { RecipeResultAdapter(collectRecipe(), LayoutInflater.from(this)) }

    private fun collectRecipe(): List<Recipe> {
        val gson = Gson()
        val stringResponse = intent.getStringExtra("RecipeResponse")
        val listOfRecipe = stringResponse?.let {
            val recipeResponse = gson.fromJson(it, RecipeResponse::class.java)
            recipeResponse.recipes
        } ?: emptyList()

        if (listOfRecipe.isEmpty()){
            Log.e("error", "Failed to parse recipe data")
        }

        return listOfRecipe
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        collectRecipe()
        initialize()
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
        binding.csvRecipeDetail.adapter = adapter
        binding.csvRecipeDetail.itemAnimator.apply {
            if (this is DefaultItemAnimator){
                supportsChangeAnimations = false
            }
        }
    }

    private fun paginate(){
        val old = adapter.getRecipeList()
        val new = adapter.
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: Card Dragged!")
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