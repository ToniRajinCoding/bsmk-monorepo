package com.example.besokmasak.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.besokmasak.R
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.databinding.FavoriteRecipeCardBinding
import com.example.besokmasak.databinding.RecipeItemListBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>(){

    private var listData = ArrayList<Recipes>()

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FavoriteRecipeCardBinding.bind(itemView)
        fun bind(data:Recipes){
            with(binding){
                tvFavTitle.text = data.recipe_name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorite_recipe_card, parent, false))

    override fun getItemCount(): Int = listData.size

    fun setItem(newListData: List<Recipes>){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }


}