package test.example.data.model

import test.example.domain.entity.Alias
import test.example.domain.entity.Favorite
import test.example.domain.entity.Urls

fun AliasModel.toAlias(): Alias =
    Alias(
        id = this.alias.toLong(),
        urls = this.links.toUrls()
    )

fun LinksModel.toUrls(): Urls =
    Urls(
        self = this.self,
        short = this.short
    )

fun Favorite.toUrlModel(): UrlModel =
    UrlModel(
        url = this.url
    )

fun UrlModel.toFavorite(): Favorite =
    Favorite(
        url = this.url
    )