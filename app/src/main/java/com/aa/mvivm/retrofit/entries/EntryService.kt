package com.aa.mvivm.retrofit.entries

import com.aa.mvivm.model.EntryList
import retrofit2.Response
import retrofit2.http.GET

interface EntryService {

    @GET("entries")
    suspend fun getEntries(): Response<EntryList>
}