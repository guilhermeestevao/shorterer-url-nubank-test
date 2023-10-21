package test.example.domain

data class UrlDto(val url: String)

data class AliasDto(
    val alias: String,
    val _links: LinksDto
)

data class LinksDto(
    val self: String,
    val short: String
)