package com.aa.mvivm.main.repo

import com.aa.mvivm.model.EntryList
import com.aa.mvivm.retrofit.entries.EntryService

//repo class is responsible for actually handling network request
class EntryRepo(private val service: EntryService) {

    suspend fun getEntries(): EntryList? {
        val response = service.getEntries()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
