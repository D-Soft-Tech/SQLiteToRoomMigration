package com.dsofttech.migratesqlitedbtoroom

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dsofttech.migratesqlitedbtoroom.data.DbFactory
import com.dsofttech.migratesqlitedbtoroom.data.model.Book
import com.dsofttech.migratesqlitedbtoroom.databinding.ActivityAddBookBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AddBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBookBinding
    private lateinit var dbHelper: DbHelperClass
    private lateinit var dbFactory: DbFactory
    private lateinit var submitBtn: Button
    private lateinit var bookTitleEt: EditText
    private lateinit var bookAuthorEt: EditText
    private lateinit var bookPages: EditText
    private val ioDispatcher = Schedulers.io()
    private val mainThreadDispatcher = AndroidSchedulers.mainThread()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_book)
        initViews()
        dbHelper = DbHelperClass(this)
        dbFactory = DbFactory(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        submitBtn.setOnClickListener {
            val book = Book(
                null,
                title = bookTitleEt.text.toString().trim(),
                author = bookAuthorEt.text.toString().trim(),
                numOfPages = bookPages.text.toString().trim().toInt(),
            )

            // SQLiteOpenHelper implementation
            // val response = dbHelper.addBook(book)
            // if (response == "Book inserted successfully") { //Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
            //    finish()
            // } else {
            //    Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
            // }

            // Room implementation

            compositeDisposable.add(
                dbFactory.bookDao.addBook(book)
                    .subscribeOn(ioDispatcher)
                    .observeOn(mainThreadDispatcher)
                    .subscribe { affectedRow, error ->
                        affectedRow?.let {
                            if (it > 0) {
                                Toast.makeText(
                                    this,
                                    "Book inserted sucessfully",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Error inserting book into database, kindly try again",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }
                        }

                        error?.let {
                            it.localizedMessage?.let { it1 -> Log.d("ERROR", it1) }
                        }
                    },
            )
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
