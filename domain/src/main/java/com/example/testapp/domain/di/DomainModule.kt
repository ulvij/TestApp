package com.example.testapp.domain.di

import com.example.testapp.domain.exception.ErrorConverter
import com.example.testapp.domain.exception.ErrorConverterImpl
import com.example.testapp.domain.exception.ErrorMapper
import com.example.testapp.domain.repository.TvShowsRepository
import com.example.testapp.domain.usecase.LoadTvShowsUseCase
import com.example.testapp.domain.usecase.ObserveTvShowsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import kotlin.coroutines.CoroutineContext


const val IO_CONTEXT = "IO_CONTEXT"
const val ERROR_MAPPER_NETWORK = "ERROR_MAPPER_NETWORK"

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideErrorConverter(@Named(ERROR_MAPPER_NETWORK) errorMapper: ErrorMapper): ErrorConverter {
        return ErrorConverterImpl(setOf(errorMapper))
    }

    @Provides
    fun provideLoadTvShowsUseCase(
        errorConverter: ErrorConverter,
        @Named(IO_CONTEXT) coroutineContext: CoroutineContext,
        tvShowsRepository: TvShowsRepository
    ): LoadTvShowsUseCase {
        return LoadTvShowsUseCase(coroutineContext, errorConverter, tvShowsRepository)
    }

    @Provides
    fun provideObserveTvShowsUseCase(
        errorConverter: ErrorConverter,
        @Named(IO_CONTEXT) coroutineContext: CoroutineContext,
        tvShowsRepository: TvShowsRepository
    ): ObserveTvShowsUseCase {
        return ObserveTvShowsUseCase(coroutineContext, errorConverter, tvShowsRepository)
    }
}