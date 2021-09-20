package com.stjepanbarisic.a3hnlistok.di

import com.stjepanbarisic.a3hnlistok.viewmodels.GamesViewModel
import com.stjepanbarisic.a3hnlistok.viewmodels.MainViewModel
import com.stjepanbarisic.a3hnlistok.viewmodels.ResultsTableViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ResultsTableViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { GamesViewModel(get(), get()) }
}
