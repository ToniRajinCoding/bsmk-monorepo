package com.example.besokmasak.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.besokmasak.R
import com.example.besokmasak.databinding.FragmentSearchBinding
import com.example.besokmasak.searchresult.RecipeResultActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.snackbar.Snackbar

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private var mInterstitialAd: InterstitialAd? = null
    private val adRequest by lazy { AdRequest.Builder().build() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        val intent = Intent(requireActivity().applicationContext, RecipeResultActivity::class.java)

        loadAds()

        binding.btnSubmit.setOnClickListener {

            with(binding) {
                val ingredients = binding.etIngredients.text.toString()
                val method = binding.etMethod.text.toString()
                if (etIngredients.text.toString().isBlank() || etMethod.text.toString().isBlank()) {
                    val message = "Please input your ingredients and method"
                    val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
                    with(snackbar) {
                        setBackgroundTint(context.getColor(R.color.darker_teal))
                        setTextColor(context.getColor(R.color.white))
                        show()
                    }
                } else {
                    if (mInterstitialAd != null) {
                        //Load Ads
                        mInterstitialAd?.show(requireActivity())
                        //Set Ads Callback
                        mInterstitialAd?.fullScreenContentCallback =
                            object : FullScreenContentCallback() {
                                override fun onAdDismissedFullScreenContent() {
                                    // Called when ad is dismissed.
                                    Log.d("TAG", "Ad dismissed fullscreen content.")
                                    mInterstitialAd = null
                                    loadAds()
                                    intent.putExtra("ingredients", ingredients)
                                    intent.putExtra("method", method)
                                    startActivity(intent)
                                }
                            }
                    } else {
                        Log.d("TAG", "The Ads wasn't ready yet")
                    }
                }
            }
        }

        return binding.root
    }

    private fun loadAds(){
        InterstitialAd.load(
            requireContext(),
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }
            })
    }


}