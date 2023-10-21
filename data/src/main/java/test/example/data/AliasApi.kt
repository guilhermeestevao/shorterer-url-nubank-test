package test.example.data

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import test.example.domain.AliasDto
import test.example.domain.UrlDto


interface AliasApi {

    @POST("/alias")
    suspend fun shortenUrl(@Body urlDto: UrlDto): AliasDto

    @POST("/alias/{id}")
    suspend fun findUrlByAlias(@Path("id") id: String): UrlDto

}