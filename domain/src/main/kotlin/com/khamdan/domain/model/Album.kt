package com.khamdan.domain.model

data class Album(
    val id: Int,
    val title: String,
    val userId: Int,
    val photos: List<Photo> = listOf()
)