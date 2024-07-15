package com.example.besokmasak.searchresult

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.besokmasak.core.data.source.Resource
import com.example.besokmasak.databinding.ActivityRecipeResultBinding
import com.example.besokmasak.utils.AdmobManager
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeResultActivity : AppCompatActivity(), CardStackListener {

    @Inject
    lateinit var admobManager: AdmobManager
    private lateinit var binding: ActivityRecipeResultBinding
    private val manager by lazy { CardStackLayoutManager(this, this) }

    private val viewModel: RecipeResultViewModel by viewModels()
    private lateinit var adapter: RecipeResultAdapter
    //private val adRequest by lazy { AdRequest.Builder().build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get intent string from search
        val ingredients = intent.getStringExtra("ingredients")
        val method = intent.getStringExtra("method")
        Log.d("ingredient method logging : ", "$ingredients & $method")

        //setup ads
        admobManager.loadAds()
//        val adRequest = AdRequest.Builder().build()
//        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
//            override fun onAdFailedToLoad(adError: LoadAdError) {
//                Log.d("TAGGGG", adError.toString())
//                mInterstitialAd = null
//            }
//
//            override fun onAdLoaded(interstitialAd: InterstitialAd) {
//                Log.d("TAGGGGG", "Ad was loaded.")
//                mInterstitialAd = interstitialAd
//            }
//        })

        //show ads when user reached the view
//        if (mInterstitialAd != null) {
//            mInterstitialAd?.show(this)
//        } else {
//            Log.d("TAG", "The interstitial ad wasn't ready yet.")
//        }


        //initialize the cardStackView
        initialize()

        //set adapter and pagination
        adapter = RecipeResultAdapter(viewModel)
        var paginationCounter = viewModel._paginationCounter.value?.plus(1) ?: 1

        //if the intent passed successfully
        if (ingredients != null && method != null) {
            viewModel.searchQuery(ingredients, method)
            viewModel.recipesLiveData.observe(this) { resource ->
                when (resource) {
                    is Resource.Success -> {
                        binding.loadingBar.visibility = View.INVISIBLE
                        binding.csvRecipeDetail.visibility = View.VISIBLE
                        binding.tvTips.visibility = View.INVISIBLE

                        val recipes = resource.data!!

                        //check if its a initial cardStackView or just paginating
                        if (paginationCounter > 1) {
                            //Pagination
                            binding.csvRecipeDetail.post {
                                adapter.generateMoreRecipeList(recipes)
                            }
                        } else {
                            //initialize CardStack View
                            adapter = RecipeResultAdapter(viewModel)
                            adapter.setRecipeList(recipes)
                            binding.csvRecipeDetail.adapter = adapter
                            paginationCounter += 1
                        }
                    }

                    is Resource.Loading -> {
                        //show loading indicator
                        Log.d("LODENG", "Resource Loading Running!")
                        binding.loadingBar.visibility = View.VISIBLE
                        binding.csvRecipeDetail.visibility = View.VISIBLE
                        binding.tvTips.visibility = View.VISIBLE
                    }

                    is Resource.Error -> {
                        with(binding.tvTips) {
                            text = "There's a problem generating recipe, please try again. " +
                                    "\nIf issue persist, please contact tonirajincoding@gmail.com"
                            visibility = View.VISIBLE
                        }
                        binding.loadingBar.visibility = View.INVISIBLE

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
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    private fun paginate() {
        val ingredients = intent.getStringExtra("ingredients")
        val method = intent.getStringExtra("method")
        if (ingredients != null && method != null) {
            viewModel._recipesLiveData.postValue(Resource.Loading())
            viewModel.searchQuery(ingredients, method)
        }
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
        Log.d("posisi item : ", manager.topPosition.toString())
        Log.d("jumlah item yg di generate : ", adapter.itemCount.toString())

        var mInterstitialAd: InterstitialAd? = admobManager.getInterstitialAd()
        if ((manager.topPosition + 1) == adapter.itemCount) {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(this)
                mInterstitialAd.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        Log.d("TAG", "Ad dismissed fullscreen content.")
                        mInterstitialAd = null
                        admobManager.loadAds()
                        paginate()
                    }
                }
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
        }

    }


}