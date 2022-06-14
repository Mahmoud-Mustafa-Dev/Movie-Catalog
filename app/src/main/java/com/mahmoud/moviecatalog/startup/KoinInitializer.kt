package com.mahmoud.moviecatalog.startup

import android.content.Context
import androidx.startup.Initializer
import com.mahmoud.moviecatalog.ApplicationModuleProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class KoinInitializer: Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication = startKoin {
        androidContext(context.applicationContext)
        modules(ApplicationModuleProvider.getModules())
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
