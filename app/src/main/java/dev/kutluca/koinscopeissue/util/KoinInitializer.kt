package dev.kutluca.koinscopeissue.util

import android.content.Context
import androidx.startup.Initializer
import dev.kutluca.koinscopeissue.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.option.viewModelScopeFactory

class KoinInitializer : Initializer<Unit> {
    @OptIn(KoinExperimentalAPI::class)
    override fun create(context: Context) {
        startKoin {
            options(
                viewModelScopeFactory(),
            )
            androidContext(context)
            modules(appModules)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}

interface KoinDependentInitializer :
    Initializer<Unit>,
    KoinComponent {
    override fun dependencies(): List<Class<out Initializer<*>>> = listOf(KoinInitializer::class.java)
}
