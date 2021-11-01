package com.example.testapp.data.di

import android.app.Application
import com.example.testapp.data.BuildConfig
import com.example.testapp.data.converter.IntListConverter
import com.example.testapp.data.converter.StringListConverter
import com.example.testapp.data.error.RemoteErrorMapper
import com.example.testapp.data.local.TvShowDao
import com.example.testapp.data.local.TvShowDatabase
import com.example.testapp.data.local.TvShowLocalDataSourceImpl
import com.example.testapp.data.local.TvShowLocalDateSource
import com.example.testapp.data.remote.TvShowApi
import com.example.testapp.data.repository.TvShowsRepositoryImpl
import com.example.testapp.domain.di.ERROR_MAPPER_NETWORK
import com.example.testapp.domain.di.IO_CONTEXT
import com.example.testapp.domain.exception.ErrorMapper
import com.example.testapp.domain.repository.TvShowsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowApi: TvShowApi,
        tvShowLocalDataSource: TvShowLocalDateSource
    ): TvShowsRepository {
        return TvShowsRepositoryImpl(tvShowApi, tvShowLocalDataSource)
    }

    //   Network

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )

        val builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(false)
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        return builder.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideTvShowApi(retrofit: Retrofit): TvShowApi =
        retrofit.create(TvShowApi::class.java)


    // Local
    @Singleton
    @Provides
    fun provideTvShowDataSource(
        tvShowDao: TvShowDao
    ): TvShowLocalDateSource {
        return TvShowLocalDataSourceImpl(tvShowDao)
    }

    @Singleton
    @Provides
    fun provideTvShowDao(database: TvShowDatabase): TvShowDao = database.tvShowDao()

    @Singleton
    @Provides
    fun provideTvShowDatabase(
        application: Application,
        moshi: Moshi,
    ): TvShowDatabase {
        return TvShowDatabase.getInstance(
            application,
            StringListConverter(moshi),
            IntListConverter(moshi),
        )
    }

    //  General

    @Provides
    @Named(IO_CONTEXT)
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    @Named(ERROR_MAPPER_NETWORK)
    fun provideErrorMapper(moshi: Moshi): ErrorMapper {
        return RemoteErrorMapper(moshi)
    }


}