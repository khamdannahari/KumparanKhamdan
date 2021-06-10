package com.khamdan.domain.repository

import com.khamdan.domain.model.Album
import com.khamdan.domain.model.Comment
import com.khamdan.domain.model.Photo
import com.khamdan.domain.model.Post
import com.khamdan.domain.model.User
import io.reactivex.rxjava3.core.Single

interface PostRepository {

    fun getListPost(): Single<List<Post>>

    fun getUser(userId: Int): Single<User>

    fun getPost(postId: Int): Single<Post>

    fun getListComment(postId: Int): Single<List<Comment>>

    fun getListAlbum(userId: Int): Single<List<Album>>

    fun getListPhoto(albumId: Int): Single<List<Photo>>
}