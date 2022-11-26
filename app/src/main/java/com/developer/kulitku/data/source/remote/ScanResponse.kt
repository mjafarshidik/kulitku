package com.developer.kulitku.data.source.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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
	val saran: List<SuggestionResponse>,

	@field:SerializedName("kandungan")
	val kandungan: List<RecommendationIngredientResponse>
): Parcelable

@Parcelize
data class RecommendationIngredientResponse(
	var title: String = "",
): Parcelable

@Parcelize
data class SuggestionResponse(
	var title: String = "",
): Parcelable
