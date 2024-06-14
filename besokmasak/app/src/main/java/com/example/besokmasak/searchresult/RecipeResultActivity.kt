package com.example.besokmasak.searchresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.besokmasak.ui.RecipeResultAdapter
import com.example.besokmasak.databinding.ActivityRecipeResultBinding
import com.example.besokmasak.ui.RecipeViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeResultActivity : AppCompatActivity(), CardStackListener {

    private lateinit var binding: ActivityRecipeResultBinding
    private lateinit var adapter: RecipeResultAdapter

    private val manager by lazy { CardStackLayoutManager(this, this) }
    //private val adapter by lazy { RecipeResultAdapter(collectRecipe(), LayoutInflater.from(this)) }
    private val viewModel : RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.generateRecipe()
        adapter = RecipeResultAdapter(viewModel.listOfRecipe, LayoutInflater.from(this))
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
        viewModel.generateRecipe()
        val new = old.plus(viewModel.listOfRecipe)
        adapter.updateRecipeList(new)
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