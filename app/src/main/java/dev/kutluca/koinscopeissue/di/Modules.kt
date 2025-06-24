package dev.kutluca.koinscopeissue.di

import dev.kutluca.koinscopeissue.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModules =
    module {
        viewModelOf(::HomeViewModel)
    }
