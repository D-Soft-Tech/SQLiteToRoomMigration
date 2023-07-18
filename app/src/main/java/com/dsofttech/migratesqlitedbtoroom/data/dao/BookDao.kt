package com.dsofttech.migratesqlitedbtoroom.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsofttech.migratesqlitedbtoroom.data.model.Book
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBook(book: Book): Single<Long>

    @Query("SELECT * FROM testBook")
    fun fetchBooks(): Observable<List<Book>>
}
