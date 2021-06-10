package com.khamdan.domain.model

data class UserDetailData(
    val user:User?,
    val albums: List<Album> = listOf()
)