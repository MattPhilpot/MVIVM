package com.aa.mvivm.di

import com.aa.mvivm.main.repo.EntryRepo
import com.aa.mvivm.retrofit.entries.EntryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {


    @Provides
    @Singleton
    fun provideEntryRepo(entryService: EntryService): EntryRepo = EntryRepo(entryService)
}