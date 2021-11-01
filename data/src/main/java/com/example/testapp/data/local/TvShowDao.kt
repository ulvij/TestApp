package com.example.testapp.data.local

import androidx.room.*
import com.example.testapp.data.local.models.TvShowLocalDto
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

    @Query("SELECT * from tv_show_table")
    fun getTvShows(): Flow<List<TvShowLocalDto>>

    @Query("SELECT * from tv_show_table WHERE tv_show_id = :id")
    fun getTvShowById(id: Long): Flow<TvShowLocalDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(list: List<TvShowLocalDto>)

    @Query("DELETE FROM tv_show_table")
    fun clearData()

}