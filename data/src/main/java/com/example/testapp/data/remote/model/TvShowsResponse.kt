package com.example.testapp.data.remote.model

data class TvShowsResponse(
    val page: Int?,
    val results: List<TvShowRemoteDto>,
    val total_pages: Int,
    val total_results: Int
)