package com.example.fitfit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitfitApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}