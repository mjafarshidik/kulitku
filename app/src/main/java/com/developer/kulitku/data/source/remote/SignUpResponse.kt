package com.developer.kulitku.data.source.remote

import com.google.gson.annotations.SerializedName

data class SignUpResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("message")
	val message: String? = null
)
