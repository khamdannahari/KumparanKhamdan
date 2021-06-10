package com.khamdan.domain.usecases

import com.khamdan.domain.model.Post
import com.khamdan.domain.repository.PostRepository
import com.khamdan.domain.usecases.base.Logger
import com.khamdan.domain.usecases.base.SingleUseCase
import com.khamdan.domain.usecases.base.UseCaseScheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetPost
@Inject internal constructor(
    private val postRepository: PostRepository,
    useCaseScheduler: UseCaseScheduler? = null, logger: Logger? = null
) : SingleUseCase<Post, Int>(useCaseScheduler, logger) {

    override fun build(param: Int): Single<Post> = postRepository.getPost(param)

}
