package com.example.testapp.domain.model

data class TvShow(
    val posterPath: String?,
    val popularity: Double?,
    val id: Long?,
    val localId: Long?,
    val backdropPath: String?,
    val voteAverage: Double?,
    val overview: String?,
    val firstAirDate: String?,
    val originCountry: List<String>?,
    val genreIds: List<Int>?,
    val originalLanguage: String?,
    val voteCount: Int?,
    val name: String?,
    val originalName: String?,
)