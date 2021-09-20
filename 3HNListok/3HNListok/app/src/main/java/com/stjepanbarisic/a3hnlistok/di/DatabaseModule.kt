package com.stjepanbarisic.a3hnlistok.di

import androidx.room.Room
import com.stjepanbarisic.a3hnlistok.data.database.HNListokDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DATABASE_NAME = "HNListokDatabase"

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), HNListokDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .addMigrations()
            .build()
    }

    single { get<HNListokDatabase>().footballTeamDao() }
    single { get<HNListokDatabase>().gameDao() }
    single { get<HNListokDatabase>().gameWithFootballTeamsDao() }
}