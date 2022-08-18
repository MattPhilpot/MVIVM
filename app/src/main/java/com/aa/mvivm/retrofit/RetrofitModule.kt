package com.aa.mvivm.retrofit

import com.aa.mvivm.retrofit.entries.EntryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideEntryService(retrofit: Retrofit): EntryService {
        return retrofit.create(EntryService::class.java)
    }
}