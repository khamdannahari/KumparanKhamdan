package com.khamdan.data.repository

import com.khamdan.data.mapper.RepoMapper
import com.khamdan.data.net.api.ApiInterface
import com.khamdan.domain.model.Album
import com.khamdan.domain.model.Comment
import com.khamdan.domain.model.Photo
import com.khamdan.domain.model.Post
import com.khamdan.domain.model.User
import com.khamdan.domain.repository.PostRepository
import io.reactivex.rxjava3.core.Single

class PostDataRepository(
    private val apiInterface: ApiInterface,
    private val repoMapper: RepoMapper
) : PostRepository {

    override fun getListPost(): Single<List<Post>> =
        apiInterface.getListPost()
            .map { postsDTO -> repoMapper.transform(postsDTO)}

    override fun getUser(userId: Int): Single<User> =
        apiInterface.getUser(userId)
            .map { userDTO -> repoMapper.transform(userDTO) }

    override fun getPost(postId: Int): Single<Post> =
        apiInterface.getPost(postId)
            .map { postDTO -> repoMapper.transform(postDTO) }

    override fun getListComment(postId: Int): Single<List<Comment>> =
        apiInterface.getListComment(postId)
            .map { comments -> comments.map { commentDTO -> repoMapper.transform(commentDTO) } }

    override fun getListAlbum(userId: Int): Single<List<Album>> =
        apiInterface.getListAlbum(userId)
            .map { albums -> albums.map { albumDTO -> repoMapper.transform(albumDTO) } }

    override fun getListPhoto(albumId: Int): Single<List<Photo>> =
        apiInterface.getListPhoto(albumId)
            .map { photo -> photo.map { photoDTO -> repoMapper.transform(photoDTO) } }
}
