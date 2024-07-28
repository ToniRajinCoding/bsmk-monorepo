package com.example.besokmasak.favorite

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.besokmasak.R
import com.example.besokmasak.core.domain.model.Recipes
import com.example.besokmasak.databinding.FavoriteRecipeCardBinding
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable

class FavoriteAdapter(private val viewmodel: FavoriteViewModel, private val context: Context) : RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>(){

    private var listData = ArrayList<Recipes>()

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FavoriteRecipeCardBinding.bind(itemView)
        fun bind(data:Recipes, viewmodel: FavoriteViewModel, context: Context){
            with(binding){
                tvFavTitle.text = data.recipe_name
                itemView.setOnClickListener{
                    val intent = Intent(context, FavoriteActivity::class.java)
//                    intent.putExtra("RECIPE_NAME", data.recipe_name)
                    intent.putExtra("RECIPES", data as Serializable)
                    context.startActivity(intent)
                }
                favBtn.setOnClickListener {
                    viewmodel.updateFavoriteState(data,false)
                    val message = R.string.removed_fav
                    val snackbar = Snackbar.make(itemView, message, Snackbar.LENGTH_SHORT)
                    with(snackbar){
                        setBackgroundTint(context.getColor(R.color.darker_teal))
                        setTextColor(context.getColor(R.color.white))
                        show()
                    }

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