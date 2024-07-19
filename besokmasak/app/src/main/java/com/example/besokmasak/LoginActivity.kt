package com.example.besokmasak

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.besokmasak.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.BuildConfig
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.util.Properties

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val RC_SIGN_IN = 9001
    private val mAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gAuthToken = AuthTokenPref.getGoogleAuthToken(this)

        if(gAuthToken!=null){

        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(com.example.besokmasak.BuildConfig.WEB_CLIENT_ID)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.btnSignIn.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN)
        }

//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
//        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
//            .setFilterByAuthorizedAccounts(false)
//            .setServerClientId("656523438487-2qa9s52pmhu1atbguukr30bjd160q6bs.apps.googleusercontent.com")
//            .setAutoSelectEnabled(true)
//            .build()
//
//        val request: androidx.credentials.GetCredentialRequest =
//            androidx.credentials.GetCredentialRequest.Builder().addCredentialOption(googleIdOption)
//                .build()
//
//        val context = this
//
//        val credentialManager = androidx.credentials.CredentialManager.create(context)
//
//        lifecycleScope.launch {
//            try {
//                val result = credentialManager.getCredential(request = request, context = context)
//                handleSignIn(result)
//            }catch (e: GetCredentialException){
//                Log.e("Tag", e.toString())
//            }
//        }
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                account.idToken?.let { firebaseAuthWithGoogle(it) }

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("WOW", "Logged in! Nice!!! Now check firebase")

                    val intentLA = Intent(this, LanguageActivity::class.java)
                    startActivity(intentLA)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("SED", "Gagal Ngab")
                    finish()
                }
            }
    }

//    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
//    fun handleSignIn(result: androidx.credentials.GetCredentialResponse){
//        when(val credential = result.credential){
//            is CustomCredential -> {
//                if(credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
//                    try {
//                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
//                    }catch (e: GoogleIdTokenParsingException){
//                        Log.e("TAG", "Received an invalid google id token response", e)
//                    }
//                }else{
//                    Log.e("TAG", "Unexpected type of credential")
//                }
//            }
//
//            else -> {
//                Log.e("TAG", "Unexpected type of credential")
//            }
//        }
//    }

}