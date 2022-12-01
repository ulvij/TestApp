package com.example.testapp.data.remote.model

data class TvShowRemoteDto(
    var poster_path: String?,
    var popularity: Double?,
    var id: Long?,
    var backdrop_path: String?,
    var vote_average: Double?,
    var overview: String?,
    var first_air_date: String?,
    var origin_country: List<String>?,
    var genre_ids: List<Int>?,
    var original_language: String?,
    var vote_count: Int?,
    var name: String?,
    var original_name: String?,
)