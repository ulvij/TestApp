package com.example.testapp.data.local.models

import androidx.room.*
import com.example.testapp.data.converter.IntListConverter
import com.example.testapp.data.converter.StringListConverter

@Entity(tableName = "tv_show_table")
data class TvShowLocalDto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "tv_show_id") val tvShowId: Long,
    @ColumnInfo(name = "poster_path") val posterPath: String? = null,
    @ColumnInfo(name = "popularity") val popularity: Double? = null,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String? = null,
    @ColumnInfo(name = "vote_average") val voteAverage: Double? = null,
    @ColumnInfo(name = "overview") val overview: String? = null,
    @ColumnInfo(name = "first_air_date") val firstAirDate: String? = null,
    @TypeConverters(StringListConverter::class) @ColumnInfo(name = "origin_country") val originCountry: List<String>? = null,
    @TypeConverters(IntListConverter::class) @ColumnInfo(name = "genre_ids") val genreIds: List<Int>? = null,
    @ColumnInfo(name = "original_language") val originalLanguage: String? = null,
    @ColumnInfo(name = "vote_count") val voteCount: Int? = null,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "original_name") val originalName: String? = null,
)