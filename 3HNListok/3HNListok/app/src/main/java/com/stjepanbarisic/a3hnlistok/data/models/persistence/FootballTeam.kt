package com.stjepanbarisic.a3hnlistok.data.models.persistence

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stjepanbarisic.a3hnlistok.common.base.Persistable
import com.stjepanbarisic.a3hnlistok.data.models.GameGoals
import com.stjepanbarisic.a3hnlistok.data.models.Location
import com.stjepanbarisic.a3hnlistok.data.models.wrappers.FootballTeamListItem
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class FootballTeam(
    @PrimaryKey(autoGenerate = true) val footballTeamId: Long = 0,
    val name: String,
    val image: Int,
    var wins: Int = 0,
    var losses: Int = 0,
    var draw: Int = 0,
    val location: Location,
    val goals: ArrayList<GameGoals>? = arrayListOf()
) : Persistable<FootballTeamListItem>, Parcelable {

    fun getTotalPoints(): Int {
        return wins.times(3) + draw.times(1)
    }

    fun getTotalGamesPlayed(): Int {
        return wins + losses + draw
    }

    fun getGameGoals(gameId: Long): GameGoals? {
        return goals?.find { it.gameId == gameId }
    }

    override fun asListData(): FootballTeamListItem {
        return FootballTeamListItem(
            id = footballTeamId,
            name = name,
            image = image,
            wins = wins.toString(),
            losses = losses.toString(),
            draw = draw.toString(),
            location = location,
            totalPoints = getTotalPoints().toString(),
            totalGamesPlayed = getTotalGamesPlayed().toString()
        )
    }

}