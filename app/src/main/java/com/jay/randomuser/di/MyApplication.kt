package com.jay.randomuser.di

import android.app.Application
import com.jay.randomuser.modules.remoteModule
import com.jay.randomuser.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                remoteModule,
                viewModelModule
            )
        }
    }
}