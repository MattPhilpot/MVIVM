package com.aa.mvivm.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EntryList(
    @Json(name = "count") val entryCount: Int,
    @Json(name = "entries") val entries: List<Entry>
)
