package com.khamdan.domain.model

data class PostDetailData(
    val user: User,
    val post: Post,
    val comments:List<Comment> = listOf()
)