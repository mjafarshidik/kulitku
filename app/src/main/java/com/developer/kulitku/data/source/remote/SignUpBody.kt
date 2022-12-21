package com.developer.kulitku.data.source.remote

data class SignUpBody(
    val nama: String? = null,

    val email: String? = null,

    val jenisKelamin: String? = null,

    val jenisKulit: String? = null,

    val keluhan: String? = null,

    val tanggalLahir: String? = null,

    val pass: String? = null,
)