package com.stjepanbarisic.a3hnlistok.data.database.storage

import com.stjepanbarisic.a3hnlistok.data.database.dao.GameDao
import com.stjepanbarisic.a3hnlistok.data.database.dao.GameWithFootballTeamsDao
import com.stjepanbarisic.a3hnlistok.data.models.persistence.Game
import com.stjepanbarisic.a3hnlistok.data.models.persistence.GameFootballTeamCrossRef

class GameStorage(
    private val gameDao: GameDao,
    private val gameWithFootballTeamsDao: GameWithFootballTeamsDao
) {

    fun getGamesWithFootballTeamsLiveData() =
        gameWithFootballTeamsDao.getGamesWithFootballTeamsLiveData()

    suspend fun insertGameFootballTeamCrossRef(gameFootballTeamCrossRef: GameFootballTeamCrossRef) {
        gameWithFootballTeamsDao.insertGameFootballTeamCrossRef(gameFootballTeamCrossRef)
    }

    suspend fun insertGame(game: Game): Long {
        return gameDao.insertGame(game)
    }

    suspend fun updateGame(game: Game) {
        gameDao.updateGame(game)
    }

}