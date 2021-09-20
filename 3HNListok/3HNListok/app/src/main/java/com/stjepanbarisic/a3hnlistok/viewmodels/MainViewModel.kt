package com.stjepanbarisic.a3hnlistok.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stjepanbarisic.a3hnlistok.repositories.FootballTeamRepository
import kotlinx.coroutines.launch

class MainViewModel(private val footballTeamRepository: FootballTeamRepository) :
    ViewModel() {

    fun saveDefaultFootballTeams() {
        viewModelScope.launch {
            with(footballTeamRepository) {
                if (getAllFootballTeamsAsynchronous().isNullOrEmpty()) {
                    insertFootballTeams(FootballTeamRepository.hnlTeams)
                }
            }
        }
    }
}
