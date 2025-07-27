package com.example.greenpulse_04

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [FarmingTask::class], version = 1)
abstract class FarmingDatabase : RoomDatabase() {
    abstract fun farmingTaskDao(): FarmingTaskDao

    companion object {
        @Volatile private var INSTANCE: FarmingDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): FarmingDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FarmingDatabase::class.java,
                    "farming_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
