package com.khamdan.domain.usecases

import com.khamdan.domain.model.User
import com.khamdan.domain.repository.PostRepository
import com.khamdan.domain.usecases.base.Logger
import com.khamdan.domain.usecases.base.SingleUseCase
import com.khamdan.domain.usecases.base.UseCaseScheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUser
@Inject internal constructor(
    private val postRepository: PostRepository,
    useCaseScheduler: UseCaseScheduler? = null, logger: Logger? = null
) : SingleUseCase<User, Int>(useCaseScheduler, logger) {

    override fun build(param: Int): Single<User> = postRepository.getUser(param)

}
