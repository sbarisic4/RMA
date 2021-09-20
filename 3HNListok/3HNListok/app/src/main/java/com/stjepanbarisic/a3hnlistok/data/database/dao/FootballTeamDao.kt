package com.stjepanbarisic.a3hnlistok.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.stjepanbarisic.a3hnlistok.data.models.persistence.FootballTeam

@Dao
interface FootballTeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFootballTeam(footballTeam: FootballTeam)

    @Query("SELECT * FROM FootballTeam ORDER BY footballTeamId")
    suspend fun getAllFootballTeamsAsynchronous(): List<FootballTeam>?

    @Query("SELECT * FROM FootballTeam ORDER BY footballTeamId")
    fun getAllFootballTeamsLiveData(): LiveData<List<FootballTeam>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultipleFootballTeams(footballTeams: List<FootballTeam>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFootballTeam(footballTeam: FootballTeam)

}