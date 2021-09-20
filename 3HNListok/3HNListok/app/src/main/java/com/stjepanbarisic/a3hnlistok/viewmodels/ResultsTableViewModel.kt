package com.stjepanbarisic.a3hnlistok.viewmodels

import androidx.lifecycle.ViewModel
import com.stjepanbarisic.a3hnlistok.common.base.asListData
import com.stjepanbarisic.a3hnlistok.common.extensions.map
import com.stjepanbarisic.a3hnlistok.repositories.FootballTeamRepository

class ResultsTableViewModel(footballTeamRepository: FootballTeamRepository) :
    ViewModel() {

    private val footballTeams =
        footballTeamRepository.getFootballTeamsLiveData()
    val footballTeamsAsList = footballTeams.map { list ->
        val sortedTeams = list
            .sortedWith(
                compareBy(
                    { it.getTotalPoints() },
                    { -it.getTotalGamesPlayed() },
                    { it.wins })
            )
            .reversed().asListData()
        val header = listOf(FootballTeamRepository.resultsTableHeaderAsFootballTeamItem)
        header.plus(sortedTeams)
    }

}