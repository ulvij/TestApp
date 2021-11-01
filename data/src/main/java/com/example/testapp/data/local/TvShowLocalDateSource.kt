package com.example.testapp.data.local

import com.example.testapp.data.local.models.TvShowLocalDto
import kotlinx.coroutines.flow.Flow

interface TvShowLocalDateSource {

    fun observeTvShows(): Flow<List<TvShowLocalDto>>
    fun observeTvShow(id:Long): Flow<TvShowLocalDto>
    suspend fun flushAndInsertTvShows(data: List<TvShowLocalDto>)
    suspend fun addTvShows(data: List<TvShowLocalDto>)
    suspend fun clearAll()
}