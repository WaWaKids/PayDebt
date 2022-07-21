package com.wawakidss.paydebt.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wawakidss.paydebt.data.DebtEntity
import javax.inject.Singleton

@Singleton
@Database(entities = [DebtEntity::class], version = 1, exportSchema = false)
abstract class DebtRoomDatabase : RoomDatabase() {

    abstract fun debtDao(): DebtDao

    companion object {
        @Volatile
        private var INSTANCE: DebtRoomDatabase? = null

        fun getDatabase(context: Context): DebtRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DebtRoomDatabase::class.java,
                    "debt_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}