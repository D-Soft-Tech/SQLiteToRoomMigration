package com.dsofttech.migratesqlitedbtoroom.presentation.adapter // ktlint-disable filename

import androidx.databinding.ViewDataBinding
import com.dsofttech.migratesqlitedbtoroom.data.model.Book
import com.dsofttech.migratesqlitedbtoroom.databinding.BooksRvItemLayoutBinding

class BooksRvBindingInterfaceImpl(private val item: Book) :
    BooksRvBindingInterface {
    override fun bindData(view: ViewDataBinding) {
        (view as BooksRvItemLayoutBinding).apply {
            book = item
        }
    }
}
