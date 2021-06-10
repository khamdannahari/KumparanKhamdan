package com.khamdan.data.repository

import com.khamdan.data.mapper.RepoMapper
import com.khamdan.data.net.api.ApiInterface
import com.khamdan.data.net.dto.PostDTO
import com.khamdan.domain.model.Post
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostDataRepositoryTest {

    @Mock
    private lateinit var mApiInterface: ApiInterface

    @Mock
    private lateinit var mapper: RepoMapper

    private lateinit var repository: PostDataRepository

    // Parameters
    private val postId = 1

    @Before
    fun setup() {
        repository = PostDataRepository(mApiInterface, mapper)
    }

    @Test
    fun getListPost() {
        val postList = mock<List<Post>>()
        val postListDTO = mock<List<PostDTO>>()

        whenever(mApiInterface.getListPost()).thenReturn(Single.just(postListDTO))
        whenever(mapper.transform(postListDTO)).thenReturn(postList)

        repository.getListPost().test()
            .assertValueCount(1)
            .assertResult(postList)
    }

    @Test
    fun getPost() {
        val post = mock<Post>()
        val postDTO = mock<PostDTO>()

        whenever(mApiInterface.getPost(postId)).thenReturn(Single.just(postDTO))
        whenever(mapper.transform(postDTO)).thenReturn(post)

        repository.getPost(postId).test()
            .assertValueCount(1)
            .assertResult(post)
    }
}
