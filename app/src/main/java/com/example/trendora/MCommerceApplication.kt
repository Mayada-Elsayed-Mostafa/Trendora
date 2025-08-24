package com.example.trendora

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MCommerceApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MCommerceApplication)
            modules(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}