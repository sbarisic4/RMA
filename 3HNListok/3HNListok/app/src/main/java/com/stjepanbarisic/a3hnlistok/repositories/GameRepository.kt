package com.stjepanbarisic.a3hnlistok.repositories

import com.stjepanbarisic.a3hnlistok.data.database.storage.GameStorage
import com.stjepanbarisic.a3hnlistok.data.models.persistence.Game
import com.stjepanbarisic.a3hnlistok.data.models.persistence.GameFootballTeamCrossRef

class GameRepository(private val gameStorage: GameStorage) {

    fun getGamesWithFootballTeamsLiveData() =
        gameStorage.getGamesWithFootballTeamsLiveData()

    suspend fun insertGameFootballTeamCrossRef(gameFootballTeamCrossRef: GameFootballTeamCrossRef) {
        gameStorage.insertGameFootballTeamCrossRef(gameFootballTeamCrossRef)
    }

    suspend fun insertGame(game: Game): Long {
        return gameStorage.insertGame(game)
    }

    suspend fun updateGame(game: Game) {
        gameStorage.updateGame(game)
    }

}