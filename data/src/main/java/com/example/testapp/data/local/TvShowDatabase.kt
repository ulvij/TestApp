package com.example.testapp.data.local

import android.content.Context
import androidx.room.*
import com.example.testapp.data.converter.IntListConverter
import com.example.testapp.data.converter.StringListConverter
import com.example.testapp.data.local.models.TvShowLocalDto

@Database(
    entities = [TvShowLocalDto::class],
    version = 1,
)
@TypeConverters(StringListConverter::class, IntListConverter::class)
abstract class TvShowDatabase : RoomDatabase() {

    abstract fun tvShowDao(): TvShowDao

    companion object {
        private const val DB_NAME = "tv_show_database"

        @Volatile
        private var INSTANCE: TvShowDatabase? = null

        fun getInstance(
            context: Context,
            stringTypeConverter: StringListConverter,
            intTypeConverter: IntListConverter,
        ): TvShowDatabase {

            INSTANCE?.let { return it }

            synchronized(this) {
                INSTANCE?.let { return it }

                val instance = Room
                    .databaseBuilder(context.applicationContext, TvShowDatabase::class.java, DB_NAME)
                    .addTypeConverter(stringTypeConverter)
                    .addTypeConverter(intTypeConverter)
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}