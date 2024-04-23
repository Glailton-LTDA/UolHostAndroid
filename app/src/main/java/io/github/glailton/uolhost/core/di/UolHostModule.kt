package io.github.glailton.uolhost.core.di

import io.github.glailton.uolhost.core.data.remote.response.PlayerResponseMapper
import io.github.glailton.uolhost.core.data.repository.PlayerRepository
import io.github.glailton.uolhost.core.data.repository.PlayerRepositoryImpl
import io.github.glailton.uolhost.core.utils.ApiEndpoint
import io.github.glailton.uolhost.ui.presentation.screens.home.HomeViewModel
import io.github.glailton.uolhost.ui.presentation.screens.add.AddViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uolHostModules = module {
    mapperFactory { PlayerResponseMapper() }

    single { ApiEndpoint() }
    factory { provideHttpClient() }
    factory { provideUolApi(get()) }
    single { provideRetrofit(get(), get()) }
    single<PlayerRepository> {
        PlayerRepositoryImpl(
            api = get(),
            playerMapper = getMapperOf()
        )
    }

    viewModelOf(::HomeViewModel)
    viewModelOf(::AddViewModel)
}