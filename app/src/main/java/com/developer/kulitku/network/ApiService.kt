package com.developer.kulitku.network

import com.developer.kulitku.data.source.remote.ResponseObject
import com.developer.kulitku.data.source.remote.ScanResultResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    @Multipart
    @POST("uploads")
    suspend fun uploadImage(
        @Part uploads: MultipartBody.Part,
    ): ResponseObject<ScanResultResponse>
}