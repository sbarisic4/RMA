package com.stjepanbarisic.a3hnlistok.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stjepanbarisic.a3hnlistok.data.database.dao.FootballTeamDao
import com.stjepanbarisic.a3hnlistok.data.database.dao.GameDao
import com.stjepanbarisic.a3hnlistok.data.database.dao.GameWithFootballTeamsDao
import com.stjepanbarisic.a3hnlistok.data.database.typeconverters.LocationTypeConverter
import com.stjepanbarisic.a3hnlistok.data.database.typeconverters.GameGoalsTypeConverter
import com.stjepanbarisic.a3hnlistok.data.models.persistence.FootballTeam
import com.stjepanbarisic.a3hnlistok.data.models.persistence.Game
import com.stjepanbarisic.a3hnlistok.data.models.persistence.GameFootballTeamCrossRef


@Database(
    entities = [FootballTeam::class, Game::class, GameFootballTeamCrossRef::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    LocationTypeConverter::class,
    GameGoalsTypeConverter::class
)
abstract class HNListokDatabase : RoomDatabase() {
    abstract fun footballTeamDao(): FootballTeamDao
    abstract fun gameDao(): GameDao
    abstract fun gameWithFootballTeamsDao(): GameWithFootballTeamsDao
}
