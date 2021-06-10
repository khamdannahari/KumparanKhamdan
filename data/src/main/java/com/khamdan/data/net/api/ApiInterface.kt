package com.khamdan.data.net.api

import com.khamdan.data.net.dto.AlbumDTO
import com.khamdan.data.net.dto.CommentDTO
import com.khamdan.data.net.dto.PhotoDTO
import com.khamdan.data.net.dto.PostDTO
import com.khamdan.data.net.dto.UserDTO

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("posts")
    fun getListPost() : Single<List<PostDTO>>

    @GET("users/{userId}")
    fun getUser(
        @Path("userId") userId: Int
    ) : Single<UserDTO>

    @GET("posts/{postId}")
    fun getPost(
        @Path("postId") postId: Int
    ) : Single<PostDTO>

    @GET("posts/{postId}/comments")
    fun getListComment(
        @Path("postId") postId: Int
    ) : Single<List<CommentDTO>>

    @GET("users/{userId}/albums")
    fun getListAlbum(
        @Path("userId") userId: Int
    ) : Single<List<AlbumDTO>>

    @GET("albums/{albumId}/photos")
    fun getListPhoto(
        @Path("albumId") postId: Int
    ) : Single<List<PhotoDTO>>

}
