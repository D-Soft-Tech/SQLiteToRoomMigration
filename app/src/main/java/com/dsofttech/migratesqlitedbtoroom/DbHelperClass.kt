package com.dsofttech.migratesqlitedbtoroom

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dsofttech.migratesqlitedbtoroom.data.model.Book

class DbHelperClass(
    context: Context,
    dbName: String = "book_db",
    dbVersion: Int = 1,
) : SQLiteOpenHelper(context, dbName, null, dbVersion) {
    private val tableName: String = "testBook"
    private val bookId: String = "bookId"
    private val bookTitle: String = "bookTitle"
    private val bookAuthor: String = "bookAuthor"
    private val numOfPages: String = "numOfPages"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $tableName ($bookId INTEGER PRIMARY KEY AUTOINCREMENT, $bookTitle TEXT, $bookAuthor TEXT, $numOfPages INTEGER);"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val upGradeTableQuery = "DROP TABLE IF EXISTS $tableName"
        db?.execSQL(upGradeTableQuery)
        onCreate(db)
    }

    fun addBook(book: Book): String {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.apply {
            put(bookTitle, book.title)
            put(bookAuthor, book.author)
            put(numOfPages, book.numOfPages)
        }
        val result = db.insert(tableName, null, contentValues)
        return if (result > 0) "Book inserted successfully" else "Book was not inserted, kindly try again later"
    }

    fun fetchBooks(): Cursor {
        val db = this.readableDatabase
        val readQuery = "SELECT * FROM $tableName"
        return db.rawQuery(readQuery, null)
    }
}
