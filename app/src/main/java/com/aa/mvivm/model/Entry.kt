package com.aa.mvivm.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Entry(
    @Json(name = "API") val api: String,
    @Json(name = "Description") val description: String,
    @Json(name = "Auth") val auth: String,
    @Json(name = "HTTPS") val https: Boolean,
    @Json(name = "Cors") val cores: String,
    @Json(name = "Link") val link: String,
    @Json(name = "Category") val category: String
)