package com.bhavinpracticalinfosys.di.module

import android.content.Context
import com.bhavinpracticalinfosys.BuildConfig
import com.bhavinpracticalinfosys.InfosysApp
import com.bhavinpracticalinfosys.api.ApiService
import com.bhavinpracticalinfosys.api.ConnectivityInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: InfosysApp): Context {
        return application.applicationContext
    }


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }



    @Singleton
    @Provides
    fun getRetrofitService(retroFit: Retrofit): ApiService {
        return retroFit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun getGson(): Gson {
        return GsonBuilder()
            .serializeNulls().setPrettyPrinting().setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun getOkHttpCleint(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        connectivityInterceptor: ConnectivityInterceptor,
        cache: Cache
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.cache(cache)
        httpClient.connectTimeout(1, TimeUnit.MINUTES)
        httpClient.readTimeout(1, TimeUnit.MINUTES)
        httpClient.writeTimeout(1, TimeUnit.MINUTES)
        httpClient.retryOnConnectionFailure(true)
        httpClient.addInterceptor(connectivityInterceptor)
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(httpLoggingInterceptor)
        }
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideHttpCache(context: Context): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize)
    }

    @Singleton
    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


}