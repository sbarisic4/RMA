package com.stjepanbarisic.a3hnlistok.data.models.persistence

import androidx.room.Entity

@Entity(primaryKeys = ["footballTeamId", "gameId"])
data class GameFootballTeamCrossRef(
    val footballTeamId: Long,
    val gameId: Long
)