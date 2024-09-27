package com.example.besokmasak.language

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.lifecycleScope
import com.example.besokmasak.search.SearchActivity
import com.example.besokmasak.databinding.ActivityLanguageBinding
import com.example.besokmasak.utils.LanguagePref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userLanguage = LanguagePref.getUserLanguage(this@LanguageActivity)

        if (userLanguage.isNullOrEmpty()) {
            setupLanguageButton()
        } else {
            moveToSearch()
        }
    }

    private fun setupLanguageButton() {
        binding.btnEn.setOnClickListener { selectLanguage("en") }
        binding.btnId.setOnClickListener { selectLanguage("in") }
    }

    private fun selectLanguage(language: String) {
        LanguagePref.setUserLanguage(this@LanguageActivity, language)
        updateResource(language)
    }

    private fun updateResource(lanCode: String) {
        val localeList = LocaleListCompat.create(Locale(lanCode))
        AppCompatDelegate.setApplicationLocales(localeList)
    }

    private fun moveToSearch() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
        finish()
    }

}