package com.dsofttech.migratesqlitedbtoroom.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dsofttech.migratesqlitedbtoroom.R
import com.dsofttech.migratesqlitedbtoroom.data.model.Book
import com.dsofttech.migratesqlitedbtoroom.databinding.BooksRvItemLayoutBinding

class RvAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataSet: ArrayList<Book> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = DataBindingUtil.inflate<BooksRvItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.books_rv_item_layout,
            parent,
            false,
        )
        return RvViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataBinder = BooksRvBindingInterfaceImpl(dataSet[position])
        (holder as RvViewHolder).bind(dataBinder)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: ArrayList<Book>) {
        val diff = GenericRecyclerViewDiffUtil(newData, dataSet)
        val diffResult = DiffUtil.calculateDiff(diff)
        dataSet = newData
        notifyDataSetChanged()
        diffResult.dispatchUpdatesTo(this)
    }
}
