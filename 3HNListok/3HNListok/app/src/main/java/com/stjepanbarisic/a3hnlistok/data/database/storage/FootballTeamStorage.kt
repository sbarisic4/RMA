package com.stjepanbarisic.a3hnlistok.data.database.storage

import com.stjepanbarisic.a3hnlistok.data.database.dao.FootballTeamDao
import com.stjepanbarisic.a3hnlistok.data.models.persistence.FootballTeam

class FootballTeamStorage(private val footballTeamDao: FootballTeamDao) {

    fun getFootballTeamsLiveData() = footballTeamDao.getAllFootballTeamsLiveData()

    suspend fun getAllFootballTeamsAsynchronous(): List<FootballTeam>? {
        return footballTeamDao.getAllFootballTeamsAsynchronous()
    }

    suspend fun insertFootballTeam(footballTeam: FootballTeam) {
        footballTeamDao.insertFootballTeam(footballTeam)
    }

    suspend fun updateFootballTeam(footballTeam: FootballTeam) {
        footballTeamDao.updateFootballTeam(footballTeam)
    }

    suspend fun insertFootballTeams(footballTeams: List<FootballTeam>) {
        footballTeamDao.insertMultipleFootballTeams(footballTeams)
    }

}