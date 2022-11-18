package com.developer.kulitku.data.source.remote

import com.google.gson.annotations.SerializedName

//data class ScanResultResponse(
//
//	@field:SerializedName("code")
//	val code: Int? = null,
//
//	@field:SerializedName("data")
//	val data: Data? = null,
//
//	@field:SerializedName("message")
//	val message: String? = null
//)

data class Result(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("class")
	val jsonMemberClass: String? = null
)

data class ScanResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("saran")
	val saran: List<String?>? = null,

	@field:SerializedName("kandungan")
	val kandungan: List<String?>? = null
)
