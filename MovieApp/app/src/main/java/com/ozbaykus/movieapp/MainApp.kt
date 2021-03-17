package com.ozbaykus.movieapp

import android.app.Application
import com.ozbaykus.movieapp.di.component.AppComponents
import com.ozbaykus.movieapp.di.component.DaggerAppComponents
import com.ozbaykus.movieapp.di.modules.AppModule

open class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponents = initDagger(this)
    }

    private fun initDagger(app: MainApp): AppComponents =
        DaggerAppComponents.builder()
            .appModule(AppModule(app))
            .build()

    companion object {
        lateinit var appComponents: AppComponents
    }
}