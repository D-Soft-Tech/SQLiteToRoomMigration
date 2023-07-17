package com.dsofttech.migratesqlitedbtoroom.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class RvViewHolder(private val view: ViewDataBinding) : RecyclerView.ViewHolder(view.root) {
    fun bind(
        bindingInterface: BooksRvBindingInterface,
    ) {
        bindingInterface.bindData(view)
    }
}
