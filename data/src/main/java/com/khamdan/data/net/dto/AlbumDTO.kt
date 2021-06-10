package com.khamdan.data.net.dto


import com.google.gson.annotations.SerializedName

data class AlbumDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)