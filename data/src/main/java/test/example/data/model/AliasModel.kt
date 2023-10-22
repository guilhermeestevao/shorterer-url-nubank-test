package test.example.data.model

import com.squareup.moshi.Json

data class AliasModel(
    @Json(name="alias") val alias: String,
    @Json(name="_links") val links: LinksModel
)