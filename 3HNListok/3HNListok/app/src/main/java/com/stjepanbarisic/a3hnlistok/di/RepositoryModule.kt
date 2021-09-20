package com.stjepanbarisic.a3hnlistok.di

import com.stjepanbarisic.a3hnlistok.repositories.FootballTeamRepository
import com.stjepanbarisic.a3hnlistok.repositories.GameRepository
import org.koin.dsl.module


val repositoryModule = module {
    single { FootballTeamRepository(get()) }
    single { GameRepository(get()) }
}
