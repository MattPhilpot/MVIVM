package com.aa.mvivm.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl() = "https://api.publicapis.org/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
                //you can add custom json adapters here if needed
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory, @Named("baseUrl") baseUrl: String): Retrofit {
        val okhttp = with(OkHttpClient.Builder()) {
            dispatcher(Dispatcher())
            //connectionPool(ConnectionPool()) //not needed by default

            //can set write,read, and connect timeouts here
            build()
        }

        return Retrofit.Builder()
            .client(okhttp)
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }
}