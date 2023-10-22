package test.example.data

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import test.example.data.model.AliasModel
import test.example.data.model.UrlModel

interface ShortererUrlService {

    @POST("alias")
    suspend fun shortenUrl(@Body urlModel: UrlModel): AliasModel

    @POST("alias/{id}")
    suspend fun findUrlByAlias(@Path("id") id: String): UrlModel

}