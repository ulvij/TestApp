package com.example.testapp.data.mapper

import com.example.testapp.data.local.models.TvShowLocalDto
import com.example.testapp.domain.model.TvShow


fun TvShowLocalDto.toDomain() = TvShow(
    posterPath = posterPath,
    popularity = popularity,
    id = tvShowId,
    localId = id,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    overview = overview,
    firstAirDate = firstAirDate,
    originCountry = originCountry,
    genreIds = genreIds,
    originalLanguage = originalLanguage,
    voteCount = voteCount,
    name = name,
    originalName = originalName
)