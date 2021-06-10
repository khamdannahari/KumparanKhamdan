package com.khamdan.data.net.dto


import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("address")
    val address: AddressDTO,
    @SerializedName("company")
    val company: CompanyDTO,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("website")
    val website: String
)