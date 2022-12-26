package com.developer.kulitku.network

import com.developer.kulitku.data.source.remote.*
import com.developer.kulitku.data.source.remote.kubuku.KubukuResponse
import com.developer.kulitku.data.source.remote.kulitku.KulitkuResponse
import com.developer.kulitku.data.source.remote.signin.SignInBody
import com.developer.kulitku.data.source.remote.signin.SignInResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {
    @Multipart
    @POST("uploads")
    suspend fun uploadImage(
        @Part userId: MultipartBody.Part, @Part uploads: MultipartBody.Part
    ): ResponseObject<ScanResponse>

    @POST("/register")
    suspend fun signUp(
        @Body body: SignUpBody
    ): ResponseObject<SignUpResponse>

    @POST("/login")
    suspend fun signIn(
        @Body body: SignInBody
    ): ResponseObject<SignInResponse>

    @GET("/article")
    suspend fun getAllArticle(): ResponseList<KubukuResponse>

    @GET("results/{user_id}/")
    suspend fun getHistoryScan(
        @Path("user_id") id: Int
    ): ResponseList<KulitkuResponse>
}