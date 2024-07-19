package com.example.besokmasak

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.besokmasak.databinding.ActivityLanguageBinding
import com.example.besokmasak.search.SearchActivity
import java.util.Locale

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userlanguage = LanguagePref.getUserLanguage(this)
        Log.d("ISI DARI SHAREDPREF", userlanguage ?: "KOSONG")

        if (userlanguage.isNullOrEmpty()) {
            binding.btnEn.setOnClickListener{
                val language = "en"
                LanguagePref.setUserLanguage(this,language)
                updateResource(language)
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                finish()
            }
            binding.btnId.setOnClickListener{
                val language = "in"
                LanguagePref.setUserLanguage(this,language)
                updateResource(language)
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                finish()
            }
        }else{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            finish() // Finish this activity since it's not needed
        }
    }

    private fun updateResource(lanCode:String){
        Log.d("Tag", "RESOURCE UPDATE!!")
        val local = Locale(lanCode)
        Locale.setDefault(local)
        val config = resources.configuration
        config.setLocale(local)
        resources.updateConfiguration(config,resources.displayMetrics)
        Log.d("Tag", "RESOURCE UPDATEDDD!!")

    }
}