package com.stjepanbarisic.a3hnlistok.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stjepanbarisic.a3hnlistok.common.extensions.map
import com.stjepanbarisic.a3hnlistok.data.models.GameGoals
import com.stjepanbarisic.a3hnlistok.data.models.persistence.Game
import com.stjepanbarisic.a3hnlistok.data.models.persistence.GameFootballTeamCrossRef
import com.stjepanbarisic.a3hnlistok.data.models.persistence.GameWithFootballTeams
import com.stjepanbarisic.a3hnlistok.repositories.FootballTeamRepository
import com.stjepanbarisic.a3hnlistok.repositories.GameRepository
import kotlinx.coroutines.launch

class GamesViewModel(
    private val footballTeamRepository: FootballTeamRepository,
    private val gameRepository: GameRepository
) : ViewModel() {

    private val footballTeams =
        footballTeamRepository.getFootballTeamsLiveData()
    val footballTeamsNames = footballTeams.map { list ->
        list.map { it.name }
    }

    val gamesWithFootballTeams =
        gameRepository.getGamesWithFootballTeamsLiveData()

    suspend fun createGame(
        firstFootballTeamName: String,
        secondFootballTeamName: String,
        game: Game
    ) {
        val gameId = gameRepository.insertGame(game)
        val hostTeam = footballTeams.value?.find { it.name == firstFootballTeamName }
        val guestTeam = footballTeams.value?.find { it.name == secondFootballTeamName }

        hostTeam?.let { team ->
            createGameFootballTeamCrossRef(gameId, team.footballTeamId)
            team.goals?.add(GameGoals(gameId = gameId, 0))
            footballTeamRepository.updateFootballTeam(team)
        }

        guestTeam?.let { team ->
            createGameFootballTeamCrossRef(gameId, team.footballTeamId)
            team.goals?.add(GameGoals(gameId = gameId, 0))
            footballTeamRepository.updateFootballTeam(team)
        }
    }

    private suspend fun createGameFootballTeamCrossRef(gameId: Long, footballTeamId: Long) {
        gameRepository.insertGameFootballTeamCrossRef(
            GameFootballTeamCrossRef(
                gameId = gameId,
                footballTeamId = footballTeamId
            )
        )
    }

    fun finishGame(gameWithFootballTeams: GameWithFootballTeams) {
        viewModelScope.launch {
            gameWithFootballTeams.footballTeams.forEach { team ->
                footballTeamRepository.updateFootballTeam(team)
            }
            gameRepository.updateGame(gameWithFootballTeams.game)
        }
    }
}
