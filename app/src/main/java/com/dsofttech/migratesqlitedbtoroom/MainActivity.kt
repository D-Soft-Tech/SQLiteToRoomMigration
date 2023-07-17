package com.dsofttech.migratesqlitedbtoroom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dsofttech.migratesqlitedbtoroom.data.model.Book
import com.dsofttech.migratesqlitedbtoroom.databinding.ActivityMainBinding
import com.dsofttech.migratesqlitedbtoroom.presentation.adapter.RvAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.install.model.AppUpdateType

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelperClass: DbHelperClass
    private lateinit var rvAdapter: RvAdapter
    private lateinit var fab: FloatingActionButton
    private lateinit var appUpdateManager: AppUpdateManager
    private val updateType = AppUpdateType.IMMEDIATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelperClass = DbHelperClass(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
        rvAdapter = RvAdapter()
        recyclerView.apply {
            adapter = rvAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        fab.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java)
            startActivity(intent)
        }
        val books = getAllBooks()
        rvAdapter.updateData(books)
    }

    private fun checkForAppUpdates() {
    }

    private fun initViews() {
        with(binding) {
            fab = floatingActionButton
            recyclerView = rv
        }
    }

    private fun getAllBooks(): ArrayList<Book> {
        val booksCursor = dbHelperClass.fetchBooks()
        val books: ArrayList<Book> = arrayListOf()
        while (booksCursor.moveToNext()) {
            val book = Book(
                booksCursor.getString(0).toInt(),
                booksCursor.getString(1),
                booksCursor.getString(2),
                booksCursor.getString(3).toInt(),
            )
            books.add(book)
        }
        return books
    }
}
