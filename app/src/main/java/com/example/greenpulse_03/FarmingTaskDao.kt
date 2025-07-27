package com.example.greenpulse_04

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.http.GET

@Dao
interface FarmingTaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: FarmingTask)

    @Query("SELECT * FROM farming_tasks")
    fun getAllTasks(): LiveData<List<FarmingTask>>

    @Query("DELETE FROM farming_tasks")
    suspend fun clearTasks()
}


