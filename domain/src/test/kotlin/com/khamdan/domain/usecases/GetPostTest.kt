package com.khamdan.domain.usecases

import com.khamdan.domain.model.Post
import com.khamdan.domain.repository.PostRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.junit.*

@RunWith(MockitoJUnitRunner::class)
class GetPostTest {

    @Mock
    private lateinit var repository: PostRepository

    // Properties
    private val postId = 1
    private val post = Post(
        body = "body",
        id = 1,
        title = "title",
        userId = 1
    )

    private val useCase by lazy { GetPost(repository) }

    @Test
    fun buildUseCase() {
        whenever(repository.getPost(postId)).thenReturn(Single.just(post))

        useCase.execute(postId).test()
            .assertValueCount(1)
            .assertResult(post)
    }

}
