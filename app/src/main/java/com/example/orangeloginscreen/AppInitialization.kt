package com.example.orangeloginscreen

import android.app.Application
import com.example.orangeloginscreen.koin.modules.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppInitialization: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AppInitialization)
            modules(appModules)
        }
    }
}