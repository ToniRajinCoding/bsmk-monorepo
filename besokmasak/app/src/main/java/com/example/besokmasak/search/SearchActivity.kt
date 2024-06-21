package com.example.besokmasak.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import com.example.besokmasak.R
import com.example.besokmasak.databinding.ActivitySearchBinding
import com.example.besokmasak.favorite.FavoriteActivity
import com.example.besokmasak.searchresult.RecipeResultActivity
import com.example.besokmasak.ui.RecipeViewModel
import com.google.android.material.navigation.NavigationView

class SearchActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivitySearchBinding
    lateinit var toggle : ActionBarDrawerToggle

    //private val viewModel by lazy { ViewModelProvider(this).get(RecipeViewModel::class.java) }

    //private val viewModel : SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        binding.navView.setNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.itemSearch -> startActivity(Intent(this,SearchActivity::class.java))
//                R.id.itemFavorite -> startActivity(Intent(this,FavoriteActivity::class.java))
//            }
//        }

        val ingredients = binding.etMaterial.text.toString()
        val method = binding.etMasakType.text.toString()
        val intent = Intent(applicationContext, RecipeResultActivity::class.java)

        binding.btnSubmit.setOnClickListener {
            intent.putExtra("ingredients", ingredients)
            intent.putExtra("method", method)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemSearch -> startActivity(Intent(this,SearchActivity::class.java))
            R.id.itemFavorite -> startActivity(Intent(this,FavoriteActivity::class.java))
        }

        return true
    }

//    private fun askRecipe(ingredients:String, method:String){
//        val gson = Gson()
//        val apiService = RetrofitClient.apiService
//        val requestBody = RecipeRequest(
//            ingredients = ingredients,
//            method = method
//        )
//
//        val call = apiService.createQuery(requestBody)
//
//        call.enqueue(object : Callback<RecipeResponse>{
//            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
//                if(response.isSuccessful){
//                    val intent = Intent(applicationContext, RecipeResultActivity::class.java)
//                    val responseString = gson.toJson(response.body())
//                    intent.putExtra("RecipeResponse", responseString)
//                    intent.putExtra("ingredients", ingredients)
//                    intent.putExtra("method", method)
//                    Log.d("hasil jsong: ", responseString)
//                    startActivity(intent)
//                }else{
//                    val errorBody = response.errorBody()?.string()
//                    Log.e("error", "API Error: $errorBody")
//                }
//            }
//            override fun onFailure(call: Call<RecipeResponse>, throwable: Throwable) {
//                Log.e("error", "error because: " + throwable.message)
//            }
//
//        })
//
//
//    }


}