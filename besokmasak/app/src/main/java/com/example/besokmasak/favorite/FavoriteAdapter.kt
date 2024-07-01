package com.example.besokmasak.favorite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.besokmasak.R
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.databinding.FavoriteRecipeCardBinding
import com.google.android.material.snackbar.Snackbar

class FavoriteAdapter(private val viewmodel: FavoriteViewModel, private val context: Context) : RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>(){

    private var listData = ArrayList<Recipes>()

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FavoriteRecipeCardBinding.bind(itemView)
        fun bind(data:Recipes, viewmodel: FavoriteViewModel, context: Context){
            with(binding){
                tvFavTitle.text = data.recipe_name
                itemView.setOnClickListener{
                    val intent = Intent(context, FavoriteActivity::class.java)
                    intent.putExtra("recipes", data)
                    context.startActivity(intent)
                }
                favBtn.setOnClickListener {
                    viewmodel.updateFavoriteState(data,data.isFavorited)
                    val message = if (data.isFavorited) "you unfavorited this recipe" else "you have favorited this recipe!"
                    val snackbar = Snackbar.make(itemView, message, Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorite_recipe_card, parent, false))

    override fun getItemCount(): Int = listData.size

    fun setItem(newListData: List<Recipes>){
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data,viewmodel, context)
    }


}