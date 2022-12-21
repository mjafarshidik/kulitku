package com.developer.kulitku.data.source.remote.signin

import com.google.gson.annotations.SerializedName

data class SignInResponse(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("jenisKelamin")
	val jenisKelamin: String? = null,

	@field:SerializedName("tanggalLahir")
	val tanggalLahir: String? = null,

	@field:SerializedName("jenisKulit")
	val jenisKulit: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("keluhan")
	val keluhan: String? = null
)
