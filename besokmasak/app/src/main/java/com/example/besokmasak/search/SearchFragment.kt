package com.example.besokmasak.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.besokmasak.R
import com.example.besokmasak.databinding.FragmentSearchBinding
import com.example.besokmasak.searchresult.RecipeResultActivity

class SearchFragment : Fragment() {

    private lateinit var binding : FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        val ingredients = binding.etIngredients.text.toString()
        val method = binding.etMethod.text.toString()
        val intent = Intent(requireActivity().applicationContext, RecipeResultActivity::class.java)

        binding.btnSubmit.setOnClickListener {
            intent.putExtra("ingredients", ingredients)
            intent.putExtra("method", method)
            startActivity(intent)
        }

        return binding.root
    }

}