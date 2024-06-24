package com.example.besokmasak.favoritedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.besokmasak.R
import com.example.besokmasak.databinding.ActivityFavoriteBinding
import com.example.besokmasak.databinding.ActivityFavoriteDetailBinding


class FavoriteDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}