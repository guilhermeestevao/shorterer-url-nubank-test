package test.example.data.remote.model

import com.squareup.moshi.Json

data class LinksModel(
    @Json(name="self") val self: String,
    @Json(name="short") val short: String
)