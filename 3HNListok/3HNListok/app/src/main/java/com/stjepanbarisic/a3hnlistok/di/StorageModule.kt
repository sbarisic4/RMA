package com.stjepanbarisic.a3hnlistok.di

import com.stjepanbarisic.a3hnlistok.data.database.storage.FootballTeamStorage
import com.stjepanbarisic.a3hnlistok.data.database.storage.GameStorage
import org.koin.dsl.module

val storageModule = module {
    single { FootballTeamStorage(get()) }
    single { GameStorage(get(), get()) }
}