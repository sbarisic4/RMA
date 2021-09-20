package com.stjepanbarisic.a3hnlistok.data.models.persistence

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameWithFootballTeams(
    @Embedded val game: Game,
    @Relation(
        parentColumn = "gameId",
        entityColumn = "footballTeamId",
        associateBy = Junction(GameFootballTeamCrossRef::class)
    )
    val footballTeams: List<FootballTeam>
) : Parcelable {

    fun getHostFootballTeam(): FootballTeam {
        return footballTeams[0]
    }

    fun getGuestFootballTeam(): FootballTeam {
        return footballTeams[1]
    }
}