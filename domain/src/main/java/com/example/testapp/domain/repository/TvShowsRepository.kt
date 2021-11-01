package com.example.testapp.domain.repository

import com.example.testapp.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {

    fun observeTvShows(): Flow<List<TvShow>>

    suspend fun loadTvShows(page: Int): Boolean

}