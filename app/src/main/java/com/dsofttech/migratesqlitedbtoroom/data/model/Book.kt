package com.dsofttech.migratesqlitedbtoroom.data.model

data class Book(
    var id: Int = 0,
    val title: String,
    val author: String,
    val numOfPages: Int,
)
