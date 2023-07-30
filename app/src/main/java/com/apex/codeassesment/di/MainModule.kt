package com.apex.codeassesment.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.apex.codeassesment.data.local.LocalDatasource
import com.apex.codeassesment.data.local.PreferencesManager
import com.apex.codeassesment.data.remote.ApiInterface
import com.apex.codeassesment.data.remote.RemoteDataSource
import com.apex.codeassesment.data.repository.UserRepository
import com.apex.codeassesment.ui.main.MainViewModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

@Module
object MainModule {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("random-user-preferences", Context.MODE_PRIVATE)
    }

    @Provides
    fun providePreferencesManager(): PreferencesManager = PreferencesManager()

    @Provides
    fun provideApiService(): ApiInterface {
        return Retrofit.Builder().baseUrl("http://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiInterface::class.java)
    }

    @Provides
    fun provideUserRepository(remoteDataSource: RemoteDataSource,localDatasource: LocalDatasource): UserRepository{
        return UserRepository(localDatasource,remoteDataSource)
    }
}