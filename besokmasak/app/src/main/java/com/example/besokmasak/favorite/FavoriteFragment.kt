package com.example.besokmasak.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelLazy
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.besokmasak.R
import com.example.besokmasak.databinding.FragmentFavoriteBinding
import com.example.besokmasak.databinding.FragmentSearchBinding
import com.example.besokmasak.ui.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewmodel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity!=null){

            val favoriteAdapter = FavoriteAdapter()
            viewmodel.favoriteRecipe.observe(viewLifecycleOwner){ dataRecipe ->
                favoriteAdapter.setItem(dataRecipe)
            }

            with(binding.rvFavorite){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteAdapter
            }

        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }

}