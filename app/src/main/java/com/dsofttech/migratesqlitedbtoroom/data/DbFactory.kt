package com.dsofttech.migratesqlitedbtoroom.data

import android.content.Context

class DbFactory(context: Context) {
    private val dbInstance = AppDatabase.getAppDatabaseInstance(context)
    val bookDao = dbInstance.getBookDao()
}
