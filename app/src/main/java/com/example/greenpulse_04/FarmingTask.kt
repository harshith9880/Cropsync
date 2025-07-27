package com.example.greenpulse_04

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "farming_tasks")
data class FarmingTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val date: String,
    var isCompleted: Boolean = false
)
