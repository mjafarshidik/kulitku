package com.developer.kulitku.network

import com.developer.kulitku.data.source.remote.ResponseObject
import com.developer.kulitku.data.source.remote.ScanResponse
import com.developer.kulitku.data.source.remote.SignUpBody
import com.developer.kulitku.data.source.remote.SignUpResponse
import com.developer.kulitku.data.source.remote.signin.SignInBody
import com.developer.kulitku.data.source.remote.signin.SignInResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
}