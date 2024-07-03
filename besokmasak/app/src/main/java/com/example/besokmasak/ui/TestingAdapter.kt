package com.example.besokmasak.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.besokmasak.R
import com.example.besokmasak.databinding.TestItemBinding

class TestingAdapter(private val list: List<String>) : RecyclerView.Adapter<TestingAdapter.TestingViewHolder>() {

    class TestingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = TestItemBinding.bind(itemView)
        fun bind(data : String){
            with(binding){
                tvFavTitle.text = data
                favBtn.setOnClickListener{
                    Log.d("favBtn clicked", "fav btn clicked! yay")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TestingViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.test_item, parent, false)
    )


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TestingViewHolder, position: Int) {
       val data = list[position]
        holder.bind(data)
    }


}