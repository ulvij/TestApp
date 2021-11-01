package com.example.testapp.data.local

import com.example.testapp.data.local.models.TvShowLocalDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TvShowLocalDataSourceImpl(
    private val tvShowDao: TvShowDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TvShowLocalDateSource {

    override fun observeTvShows(): Flow<List<TvShowLocalDto>> {
        return tvShowDao.getTvShows()
    }

    override fun observeTvShow(id: Long): Flow<TvShowLocalDto> {
        return tvShowDao.getTvShowById(id)
    }

    override suspend fun flushAndInsertTvShows(data: List<TvShowLocalDto>) {
        withContext(ioDispatcher){
            tvShowDao.clearData()
            tvShowDao.insertTvShows(data)
        }
    }

    override suspend fun addTvShows(data: List<TvShowLocalDto>) {
        withContext(ioDispatcher){
            tvShowDao.insertTvShows(data)
        }
    }

    override suspend fun clearAll() {
        withContext(ioDispatcher){
            tvShowDao.clearData()
        }
    }

}