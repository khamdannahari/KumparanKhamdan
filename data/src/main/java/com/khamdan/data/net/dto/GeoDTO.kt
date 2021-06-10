package com.khamdan.data.net.dto


import com.google.gson.annotations.SerializedName

data class GeoDTO(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
)