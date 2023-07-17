package com.dsofttech.migratesqlitedbtoroom

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dsofttech.migratesqlitedbtoroom.data.model.Book
import com.dsofttech.migratesqlitedbtoroom.databinding.ActivityAddBookBinding

class AddBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBookBinding
    private lateinit var dbHelper: DbHelperClass
    private lateinit var submitBtn: Button
    private lateinit var bookTitleEt: EditText
    private lateinit var bookAuthorEt: EditText
    private lateinit var bookPages: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_book)
        initViews()
        dbHelper = DbHelperClass(this)
    }

    override fun onResume() {
        super.onResume()
        submitBtn.setOnClickListener {
            val book = Book(
                title = bookTitleEt.text.toString().trim(),
                author = bookAuthorEt.text.toString().trim(),
                numOfPages = bookPages.text.toString().trim().toInt(),
            )
            val response = dbHelper.addBook(book)
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews() {
        with(binding) {
            submitBtn = button
            bookTitleEt = bookTitle
            bookAuthorEt = author
            bookPages = numberOfPages
        }
    }
}
