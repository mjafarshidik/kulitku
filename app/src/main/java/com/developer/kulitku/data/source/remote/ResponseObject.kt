package com.developer.kulitku.data.source.remote

import com.google.gson.annotations.SerializedName

data class ResponseObject<T>(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("data")
    val data: T?,

    @field:SerializedName("message")
    val message: String? = null
)
