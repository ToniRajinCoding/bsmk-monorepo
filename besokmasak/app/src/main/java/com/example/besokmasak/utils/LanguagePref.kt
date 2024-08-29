package com.example.besokmasak.utils

import android.content.Context
import android.content.SharedPreferences

object LanguagePref {

    private const val SHARED_PREFS_NAME = "LANGUAGE_PREFERENCE"
    private const val USER_LANGUAGE = "userLanguage"

    private fun getSharedPreferences(context: Context) : SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setUserLanguage(context: Context, language: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(USER_LANGUAGE,language)
        editor.apply()
    }

    fun getUserLanguage(context: Context) : String? {
        return getSharedPreferences(context).getString(USER_LANGUAGE, "")
    }
}