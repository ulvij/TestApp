package com.example.testapp.domain.usecase

import com.example.testapp.domain.base.BaseFlowUseCase
import com.example.testapp.domain.exception.ErrorConverter
import com.example.testapp.domain.model.TvShow
import com.example.testapp.domain.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class ObserveTvShowsUseCase(
    context: CoroutineContext,
    converter: ErrorConverter,
    private val repository: TvShowsRepository
) : BaseFlowUseCase<Unit, List<TvShow>>(context, converter) {

    override fun createFlow(params: Unit): Flow<List<TvShow>> {
        return repository.observeTvShows()
    }
}