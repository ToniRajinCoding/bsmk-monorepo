package com.example.besokmasak.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.besokmasak.databinding.RecipeItemListBinding

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