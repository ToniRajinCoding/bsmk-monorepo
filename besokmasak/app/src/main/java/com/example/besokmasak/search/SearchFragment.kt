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
import com.example.besokmasak.recipedetail.RecipeResultActivity
import com.example.besokmasak.core.di.AdmobManager
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject
    lateinit var admobManager : AdmobManager

    private lateinit var binding: FragmentSearchBinding
//    private var mInterstitialAd: InterstitialAd? = null
//    private val adRequest by lazy { AdRequest.Builder().build() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        Log.d("halo dari search Fragment: ", "Halo dari SFFFFF")
        val intent = Intent(requireActivity().applicationContext, RecipeResultActivity::class.java)

        admobManager.loadAds()

        binding.btnSubmit.setOnClickListener {

            var mInterstitialAd = admobManager.getInterstitialAd()

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
                    intent.putExtra("ingredients", ingredients)
                    intent.putExtra("method", method)
                    startActivity(intent)
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
                                    admobManager.loadAds()
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

}