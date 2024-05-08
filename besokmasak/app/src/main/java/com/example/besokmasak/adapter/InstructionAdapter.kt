package com.example.besokmasak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.besokmasak.databinding.RecipeDetailBinding
import com.example.besokmasak.databinding.RecipeItemListBinding
import com.example.besokmasak.model.response.Recipe

class InstructionAdapter(private val instructionList: List<String>) :
    RecyclerView.Adapter<InstructionAdapter.InstructionsViewHolder>() {
    class InstructionsViewHolder(val binding: RecipeItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder {
        val binding =
            RecipeItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InstructionsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return instructionList.size
    }

    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) {
        val instruction = instructionList[position]
        holder.binding.itemText.text = instruction
    }
}