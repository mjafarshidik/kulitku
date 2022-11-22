package com.developer.kulitku.data.source.remote

data class SignUpBody(
    val id: String,

    val nama: String? = null,

    val email: String? = null,

    val password: String? = null,

    val jenis_kulit: String? = null,

    val tanggal_lahir: String? = null,

    val jenis_kelamin: String? = null,
)