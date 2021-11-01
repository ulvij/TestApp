package com.example.testapp.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class IntListConverter(private val moshi: Moshi) {

    private val moshiAdapter: JsonAdapter<List<Int>> by lazy {
        val listData = Types.newParameterizedType(List::class.java, Integer::class.java)
        return@lazy moshi.adapter(listData)
    }

    @TypeConverter
    fun listToJson(value: List<Int>): String {
        return moshiAdapter.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Int>? = moshiAdapter.fromJson(value)

}