package com.example.testapp.presentation.scene.tv_show_list

import com.example.testapp.domain.model.TvShow
import com.example.testapp.domain.usecase.LoadTvShowsUseCase
import com.example.testapp.domain.usecase.ObserveTvShowsUseCase
import com.example.testapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val observeTvShowsUseCase: ObserveTvShowsUseCase,
    private val loadTvShowsUseCase: LoadTvShowsUseCase,
) : BaseViewModel<TvShowListState, Unit>() {

    companion object {
        const val START_PAGE = 1
    }

    private var page: Int = START_PAGE
    private var hasNext: Boolean = true

    init {
        observeTvShows()
        loadData()
    }

    fun isLastPage() = hasNext.not()

    private fun observeTvShows() {
        observeTvShowsUseCase.execute(Unit)
            .onEach { postState(TvShowListState.ShowTvShows(it)) }
            .launchNoLoading()
    }

    fun loadData() {
        if (isLoading() || isLastPage()) return

        loadTvShowsUseCase
            .launch(LoadTvShowsUseCase.Param(page)) {
                onSuccess = { hasNext ->
                    this@TvShowListViewModel.hasNext = hasNext
                    if (hasNext) {
                        page++
                    }
                }
                onError = {
                    this@TvShowListViewModel.hasNext = false
                }
            }
    }

    fun reloadData() {
        page = START_PAGE
        hasNext = true
        setLoading(false)
        loadData()
    }
}


sealed class TvShowListState {
    class ShowTvShows(val tvShows: List<TvShow>) : TvShowListState()
}