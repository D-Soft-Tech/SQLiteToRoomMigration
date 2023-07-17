package com.dsofttech.migratesqlitedbtoroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dsofttech.migratesqlitedbtoroom.data.dao.BookDao
import com.dsofttech.migratesqlitedbtoroom.data.model.Book
import com.dsofttech.migratesqlitedbtoroom.utils.AppConstants.DB_NAME
import com.dsofttech.migratesqlitedbtoroom.utils.AppConstants.DB_VERSION

@Database(
    entities = [Book::class],
    version = DB_VERSION,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getBookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabaseInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun MIGRATION_1_2(): Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // this should be left empty for now since the schema, i.e the structure of our entity doesn't change
            }
        }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .addMigrations(MIGRATION_1_2())
                .build()
    }
}
