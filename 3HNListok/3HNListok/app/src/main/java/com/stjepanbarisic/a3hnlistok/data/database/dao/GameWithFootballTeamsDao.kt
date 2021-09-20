package com.stjepanbarisic.a3hnlistok.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.stjepanbarisic.a3hnlistok.data.models.persistence.GameFootballTeamCrossRef
import com.stjepanbarisic.a3hnlistok.data.models.persistence.GameWithFootballTeams

@Dao
interface GameWithFootballTeamsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // ignore adding item with same id-s of primary keys
    suspend fun insertGameFootballTeamCrossRef(gameFootballTeamCrossRef: GameFootballTeamCrossRef)

    @Transaction
    @Query("SELECT * FROM Game ORDER BY startTime DESC")
    fun getGamesWithFootballTeamsLiveData(): LiveData<List<GameWithFootballTeams>>

    @Transaction
    @Query("SELECT * FROM Game where gameId=:id")
    suspend fun getGameWithFootballTeamsByIdAsynchronous(id: Long): GameWithFootballTeams?

}