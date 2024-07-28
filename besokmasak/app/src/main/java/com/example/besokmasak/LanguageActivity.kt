package com.example.besokmasak

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.besokmasak.search.SearchActivity
import com.example.besokmasak.databinding.ActivityLanguageBinding
import java.util.Locale

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userLanguage = LanguagePref.getUserLanguage(this)

        if (userLanguage.isNullOrEmpty()) {
            binding.btnEn.setOnClickListener{
                val language = "en"
                LanguagePref.setUserLanguage(this,language)
                updateResource(language)
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)

            }
            binding.btnId.setOnClickListener{
                val language = "in"
                LanguagePref.setUserLanguage(this,language)
                updateResource(language)
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)

            }
        }else{
            Log.d("user-language : ", userLanguage)
            updateResource(userLanguage)
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            finish() // Finish this activity since it's not needed
        }
    }

    private fun updateResource(lanCode:String){
        val localeList = LocaleListCompat.create(Locale(lanCode))
        AppCompatDelegate.setApplicationLocales(localeList)
    }
}