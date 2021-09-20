package com.stjepanbarisic.a3hnlistok.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.stjepanbarisic.a3hnlistok.data.models.persistence.Game

@Dao
interface
GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: Game): Long

    @Query("SELECT * FROM Game ORDER BY startTime DESC")
    suspend fun getAllGamesAsynchronous(): List<Game>?

    @Query("SELECT * FROM Game ORDER BY startTime DESC")
    fun getAllGamesLiveData(): LiveData<List<Game>>

    @Query("SELECT * FROM Game where gameId=:id")
    suspend fun getGameByIdAsynchronous(id: Long): Game?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGame(game: Game)
}