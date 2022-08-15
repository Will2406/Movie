package com.rappi.movie.data.api

import com.rappi.movie.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    private lateinit var retrofit: Retrofit

    private const val TIMEOUT_READ = 60L
    private const val TIMEOUT_WRITE = 60L
    private const val TIMEOUT_CONNECT = 60L

    private const val FIELD_API_KEY = "api_key"
    private const val FIELD_LANGUAGE = "language"
    private const val VALUE_LANGUAGE = "es-PE"

    fun createInstance(): MovieService {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(createClient())
            .build()

        return retrofit.create(MovieService::class.java)
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getLoggerInterceptor())
            .addInterceptor(getAddQueryInterceptor())
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
            .build()
    }

    private fun getLoggerInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    private fun getAddQueryInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter(FIELD_API_KEY, BuildConfig.API_KEY)
                .addQueryParameter(FIELD_LANGUAGE, VALUE_LANGUAGE)
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

}