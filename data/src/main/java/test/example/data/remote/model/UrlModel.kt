package test.example.data.remote.model

import com.squareup.moshi.Json

data class UrlModel(
    @Json(name = "url") val url: String
)