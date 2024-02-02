package io.github.glailton.uolhost

import android.app.Application
import io.github.glailton.uolhost.core.di.uolHostModules
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class UolHostApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@UolHostApplication)
            modules(uolHostModules)
        }
    }
}