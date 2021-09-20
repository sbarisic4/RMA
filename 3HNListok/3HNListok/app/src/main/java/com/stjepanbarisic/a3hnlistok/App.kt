package com.stjepanbarisic.a3hnlistok

import android.app.Application
import com.stjepanbarisic.a3hnlistok.di.databaseModule
import com.stjepanbarisic.a3hnlistok.di.repositoryModule
import com.stjepanbarisic.a3hnlistok.di.storageModule
import com.stjepanbarisic.a3hnlistok.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App :
    Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                arrayListOf(
                    viewModelModule,
                    repositoryModule,
                    databaseModule,
                    storageModule
                )
            )
        }

    }
}