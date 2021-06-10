package com.khamdan.presentation

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.facebook.stetho.Stetho
import com.khamdan.data.di.components.DaggerDataComponent
import com.khamdan.data.di.components.DataComponent
import com.khamdan.presentation.di.components.ApplicationComponent
import com.khamdan.presentation.di.components.DaggerApplicationComponent
import timber.log.Timber

/**
 * Android Main Application
 */
class AndroidApplication : Application() {

    @set:VisibleForTesting
    lateinit var appComponent: ApplicationComponent

    @VisibleForTesting
    val dataComponent: DataComponent by lazy { DaggerDataComponent.factory().create(baseContext) }

    override fun onCreate() {
        super.onCreate()

        // Init Stetho
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

        // Init Debug log
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Create App Component
        appComponent = createAppComponent()
    }

    @VisibleForTesting
    fun createAppComponent(): ApplicationComponent =
        DaggerApplicationComponent.factory()
            .create(this, dataComponent)

}
