package com.khamdan.domain.usecases

import com.khamdan.domain.model.Post
import com.khamdan.domain.repository.PostRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.junit.*

@RunWith(MockitoJUnitRunner::class)
class GetListPostTest {

    @Mock
    private lateinit var repository: PostRepository

    // Use Case
    private val useCase by lazy { GetListPost(repository) }

    @Test
    fun buildUseCase() {
        val postList = mock<List<Post>>()

        whenever(repository.getListPost()).thenReturn(Single.just(postList))

        useCase.execute(Unit).test()
            .assertValueCount(1)
            .assertResult(postList)
    }

}
