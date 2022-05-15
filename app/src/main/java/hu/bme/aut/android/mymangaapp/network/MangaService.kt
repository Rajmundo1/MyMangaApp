package hu.bme.aut.android.mymangaapp.network

import hu.bme.aut.android.mymangaapp.model.*
import retrofit2.http.*

interface MangaService {
    @POST("/auth/login")
    suspend fun login(@Body login: Login): LoginResponse

    @GET("/auth/check")
    suspend fun authCheck(): CheckResponse

    @POST("/auth/refresh")
    suspend fun authRefresh(@Body refreshToken: RefreshToken): RefreshResponse

    @GET("/manga")
    suspend fun getManga(@Query("limit") limit: Int, @Query("offset") offset: Int, @Query("title") title: String): MangaList

    @GET("/manga/{id}")
    suspend fun getMangaFromId(@Path("id") id: String): MangaById

    @GET("/cover/{mangaOrCoverId}")
    suspend fun getCover(@Path("mangaOrCoverId") id: String): Cover
}