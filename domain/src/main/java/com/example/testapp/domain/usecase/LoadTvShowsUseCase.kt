package com.example.testapp.domain.usecase

import com.example.testapp.domain.base.BaseUseCase
import com.example.testapp.domain.exception.ErrorConverter
import com.example.testapp.domain.repository.TvShowsRepository
import kotlin.coroutines.CoroutineContext

class LoadTvShowsUseCase(
    context: CoroutineContext,
    converter: ErrorConverter,
    private val tvShowsRepository: TvShowsRepository
) : BaseUseCase<LoadTvShowsUseCase.Param, Boolean>(context, converter) {

    class Param(val page: Int)

    override suspend fun executeOnBackground(params: Param): Boolean {
        return tvShowsRepository.loadTvShows(params.page)
    }
}