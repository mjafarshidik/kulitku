package com.developer.kulitku.data.source.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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

@Parcelize
data class Result(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("class")
	val jsonMemberClass: String? = null
) : Parcelable

@Parcelize
data class ScanResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("saran")
	val saran: List<String>,

	@field:SerializedName("kandungan")
	val kandungan: List<String>
): Parcelable
