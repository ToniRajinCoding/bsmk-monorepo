package com.example.besokmasak.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.besokmasak.core.data.source.remote.response.Recipe

class RecipeDiffCallBack(
    private val old: List<Recipe>,
    private val new: List<Recipe>
    ) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].recipe_name == new[newItemPosition].recipe_name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}