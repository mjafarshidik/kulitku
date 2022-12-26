package com.developer.kulitku.data.source.remote.kubuku

import com.google.gson.annotations.SerializedName

data class KubukuResponse(
    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("ArticleById")
    val articleById: String? = null,

    @field:SerializedName("urlImage")
    val urlImage: String? = null
)