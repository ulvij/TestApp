package com.example.testapp.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class StringListConverter(private val moshi: Moshi) {

    private val moshiAdapter: JsonAdapter<List<String>> by lazy {
        val listData = Types.newParameterizedType(List::class.java, String::class.java)
        return@lazy moshi.adapter(listData)
    }

    @TypeConverter
    fun listToJson(value: List<String>): String {
        return moshiAdapter.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<String>? = moshiAdapter.fromJson(value)

}