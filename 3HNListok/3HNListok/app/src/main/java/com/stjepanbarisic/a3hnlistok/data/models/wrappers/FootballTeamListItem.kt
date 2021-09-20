package com.stjepanbarisic.a3hnlistok.data.models.wrappers

import com.stjepanbarisic.a3hnlistok.common.base.ListData
import com.stjepanbarisic.a3hnlistok.data.models.Location
import com.stjepanbarisic.a3hnlistok.data.models.persistence.FootballTeam

data class FootballTeamListItem(
    val id: Long = 0,
    val name: String,
    val image: Int,
    val wins: String,
    val losses: String,
    val draw: String,
    val location: Location,
    val totalPoints: String,
    val totalGamesPlayed: String
) : ListData<FootballTeam> {

    override fun asDatabase(): FootballTeam {
        return FootballTeam(
            footballTeamId = id,
            name = name,
            image = image,
            wins = wins.toInt(),
            losses = losses.toInt(),
            draw = draw.toInt(),
            location = location
        )
    }

}