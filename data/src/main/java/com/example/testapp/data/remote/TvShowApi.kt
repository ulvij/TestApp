package com.example.testapp.data.remote

import com.example.testapp.data.BuildConfig
import com.example.testapp.data.remote.model.TvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowApi {

    @GET("tv/popular")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en_US",
        @Query("page") page: Int? = null
    ): TvShowsResponse

}