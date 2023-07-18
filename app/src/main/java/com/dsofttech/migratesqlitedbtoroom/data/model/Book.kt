package com.dsofttech.migratesqlitedbtoroom.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dsofttech.migratesqlitedbtoroom.utils.AppConstants.BOOK_AUTHOR
import com.dsofttech.migratesqlitedbtoroom.utils.AppConstants.BOOK_ID
import com.dsofttech.migratesqlitedbtoroom.utils.AppConstants.BOOK_TITLE
import com.dsofttech.migratesqlitedbtoroom.utils.AppConstants.NUM_OF_PAGES
import com.dsofttech.migratesqlitedbtoroom.utils.AppConstants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Book(
    @ColumnInfo(name = BOOK_ID)
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = BOOK_TITLE)
    val title: String?,
    @ColumnInfo(name = BOOK_AUTHOR)
    val author: String?,
    @ColumnInfo(name = NUM_OF_PAGES)
    val numOfPages: Int?,
)
