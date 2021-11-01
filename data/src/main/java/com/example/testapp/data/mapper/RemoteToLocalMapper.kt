package com.example.testapp.data.mapper

import com.example.testapp.data.local.models.TvShowLocalDto
import com.example.testapp.data.remote.model.TvShowRemoteDto

fun TvShowRemoteDto.toLocal() = TvShowLocalDto(
    tvShowId = id ?: -1,
    posterPath = poster_path ?: "",
    popularity = popularity ?: 0.0,
    backdropPath = backdrop_path ?: "",
    voteAverage = vote_average ?: 0.0,
    overview = overview ?: "",
    firstAirDate = first_air_date ?: "",
    originCountry = origin_country ?: emptyList(),
    genreIds = genre_ids ?: emptyList(),
    originalLanguage = original_language ?: "",
    voteCount = vote_count ?: 0,
    name = name ?: "",
    originalName = original_name ?: ""
)