package com.sep.byteClub

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.sep.byteClub.ui.MainActivity
import com.sep.byteClub.ui.errorActivity.ErrorActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        showErrorScreen()
    }


    private fun showErrorScreen() {
        CaocConfig.Builder
            .create()
            .backgroundMode(CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM)
            .showErrorDetails(false)
            .logErrorOnRestart(false)
            .minTimeBetweenCrashesMs(3000)
            .restartActivity(MainActivity::class.java)
            .errorActivity(ErrorActivity::class.java)
            .apply()
    }


}