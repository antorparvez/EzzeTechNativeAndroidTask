package com.antor.filtermytodos.di

import android.content.Context
import com.antor.data.repository.DataRepository
import com.antor.data.remote.RemoteApi
import com.antor.filtermytodos.common.Constants
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModules {


    @Singleton
    @Provides
    fun provideOkHttp(
        @ApplicationContext context: Context
    ) = OkHttpClient
        .Builder()
        .addInterceptor(
            ChuckerInterceptor(context)
        )
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): RemoteApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RemoteApi::class.java)
    }


    @Provides
    @Singleton
    fun provideApiRepository(api : RemoteApi): com.antor.data.repository.ApiRepository {
        return DataRepository(api)
    }
}