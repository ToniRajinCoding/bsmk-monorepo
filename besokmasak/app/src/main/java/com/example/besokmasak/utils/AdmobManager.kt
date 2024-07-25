package com.example.besokmasak.utils

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.example.besokmasak.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdmobManager (private val context: Context) {

    private var mInterstitialAd : InterstitialAd? = null
    private val adRequest by lazy { AdRequest.Builder().build()}

    init {
        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            MobileAds.initialize(context)
        }
    }

    fun loadAds(){
        InterstitialAd.load(
            context,
            BuildConfig.AD_MOB_APP_ID,
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

    fun getInterstitialAd() : InterstitialAd? {
        return mInterstitialAd
    }

}