package hu.bme.aut.android.mymangaapp.network

import com.skydoves.sandwich.ApiResponse
import hu.bme.aut.android.mymangaapp.model.*
import retrofit2.http.*

interface MangaService {
    @POST("/auth/login")
    suspend fun login(@Body login: Login): ApiResponse<LoginResponse>

    @GET("/auth/check")
    suspend fun authCheck(): ApiResponse<CheckResponse>

    @POST("/auth/refresh")
    suspend fun authRefresh(@Body refreshToken: RefreshToken): ApiResponse<RefreshResponse>

    @GET("/manga")
    suspend fun getManga(@Query("limit") limit: Int = 10, @Query("offset") offset: Int = 0, @Query("title") title: String = "", @Header("Authorization") authHeader: String): ApiResponse<MangaList>

    @GET("/manga/{id}")
    suspend fun getMangaFromId(@Path("id") id: String, @Header("Authorization") authHeader: String): ApiResponse<MangaById>

    @GET("/cover/{mangaOrCoverId}")
    suspend fun getCover(@Path("mangaOrCoverId") id: String, @Header("Authorization") authHeader: String): ApiResponse<Cover>
}