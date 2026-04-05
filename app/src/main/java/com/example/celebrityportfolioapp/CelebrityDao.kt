package com.example.celebrityportfolioapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CelebrityDao {
    @Query("SELECT * FROM celebrities")
    fun getAllCelebrities(): Flow<List<Celebrity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCelebrities(celebrities: List<Celebrity>)

    @Query("UPDATE celebrities SET likes = likes + 1 WHERE id = :celebrityId")
    suspend fun updateLikes(celebrityId: Int)

    @Query("DELETE FROM celebrities")
    suspend fun deleteAll()
}
