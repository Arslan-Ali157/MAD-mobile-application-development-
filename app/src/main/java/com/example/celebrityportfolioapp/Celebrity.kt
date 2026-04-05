package com.example.celebrityportfolioapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "celebrities")
data class Celebrity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: String,
    val description: String,
    var likes: Int = 0
)
