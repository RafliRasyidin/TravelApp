package com.rasyidin.travelapp

import android.app.Application
import com.rasyidin.travelapp.core.di.networkModule
import com.rasyidin.travelapp.core.di.repositoryModule
import com.rasyidin.travelapp.di.useCaseModule
import com.rasyidin.travelapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )

        }
    }
}