package com.khamdan.data.net.dto


import com.google.gson.annotations.SerializedName

data class PostDTO(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)