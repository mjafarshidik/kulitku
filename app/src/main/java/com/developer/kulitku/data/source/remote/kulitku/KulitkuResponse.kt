package com.developer.kulitku.data.source.remote.kulitku

import com.google.gson.annotations.SerializedName

data class KulitkuResponse(

	@field:SerializedName("namaPenyakit")
	val namaPenyakit: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("larangan")
	val larangan: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("result_id")
	val resultId: Int? = null,

	@field:SerializedName("disease_id")
	val diseaseId: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("rekomendasi")
	val rekomendasi: String? = null,

	@field:SerializedName("kandungan")
	val kandungan: String? = null,

	@field:SerializedName("urlImage")
	val urlImage: String? = null
)
