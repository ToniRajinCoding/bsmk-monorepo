package com.example.besokmasak

import android.content.Context
import android.content.SharedPreferences

object AuthTokenPref {

    private const val SHARED_PREFS_NAME = "AUTH_PREFERENCE"
    private const val USER_TOKEN = "gToken"
    private const val USER_NAME = "username"
    private const val USER_EMAIL = "email"


    private fun getSharedPreferences(context:Context) : SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setGoogleAuthToken(context: Context, idToken : String?, name : String?, email : String?){
        val editor = getSharedPreferences(context).edit()
        editor.putString(USER_TOKEN, idToken)
        editor.putString(USER_NAME, name)
        editor.putString(USER_EMAIL, email)
        editor.apply()
    }

    fun getGoogleAuthToken(context: Context) : String? {
        return getSharedPreferences(context).getString(USER_TOKEN, "")
    }
}