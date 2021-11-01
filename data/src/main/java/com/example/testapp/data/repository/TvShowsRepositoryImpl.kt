package com.example.testapp.data.repository

import com.example.testapp.data.local.TvShowLocalDateSource
import com.example.testapp.data.mapper.toDomain
import com.example.testapp.data.mapper.toLocal
import com.example.testapp.data.remote.TvShowApi
import com.example.testapp.domain.model.TvShow
import com.example.testapp.domain.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TvShowsRepositoryImpl(
    private val tvShowApi: TvShowApi,
    private val tvShowDateSource: TvShowLocalDateSource
) : TvShowsRepository {

    override fun observeTvShows(): Flow<List<TvShow>> {
        return tvShowDateSource
            .observeTvShows()
            .map { tvShows ->
                tvShows.map { tvShowLocalDto -> tvShowLocalDto.toDomain() }
            }
    }

    override suspend fun loadTvShows(page: Int): Boolean {
        val tvShowResponse = tvShowApi.getTvShows(page = page)

        val localData = tvShowResponse.results.map { tvShowRemoteDto -> tvShowRemoteDto.toLocal() }

        if (page == 1) {
            tvShowDateSource.flushAndInsertTvShows(localData)
        } else {
            tvShowDateSource.addTvShows(localData)
        }
        return page != tvShowResponse.total_pages
    }

}