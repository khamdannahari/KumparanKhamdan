package com.khamdan.data.net.dto


import com.google.gson.annotations.SerializedName

data class CompanyDTO(
    @SerializedName("bs")
    val bs: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("name")
    val name: String
)