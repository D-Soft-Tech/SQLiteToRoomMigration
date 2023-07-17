package com.dsofttech.migratesqlitedbtoroom.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsofttech.migratesqlitedbtoroom.data.model.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBook(book: Book): Long

    @Query("SELECT * FROM testBook")
    fun fetchBooks(): List<Book>
}
