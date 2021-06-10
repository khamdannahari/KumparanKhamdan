package com.khamdan.presentation.scenes.postlist

import com.khamdan.data.extensions.startWithSingle
import com.khamdan.domain.functions.DelayFunction
import com.khamdan.domain.usecases.GetListPost
import com.khamdan.domain.usecases.GetUser
import com.khamdan.presentation.exception.ErrorMessageFactory
import com.khamdan.presentation.scenes.base.presenter.APresenter
import com.khamdan.presentation.scenes.postlist.PostListViewModel.Companion
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class PostListPresenter
@Inject constructor(
    private val getListPost: GetListPost,
    private val getUser: GetUser,
    private val router: PostListRouter,
    private val scheduler: Scheduler,
    errorMessageFactory: ErrorMessageFactory
) : APresenter<PostListView, PostListViewModel>(errorMessageFactory) {

    override fun attach(view: PostListView) {
        val loadPost = view.intentLoadData().flatMap { loadPost() }
        val retryPost = view.intentErrorRetry().flatMap { loadPost() }

        subscribeViewModel(view, loadPost, retryPost)

        view.openPostDetail()
            .subscribe { post -> router.routeToPostDetail(post.id, post.userId) }
            .addTo(composite)
    }

    //region USE CASES TO VIEW MODEL
    private fun loadPost(): Observable<PostListViewModel> =
        getListPost.execute(Unit)
            .toObservable()
            .flatMapIterable { it }
            .flatMap { post ->
                getUser.execute(post.userId).toObservable().map {
                    post.copy(user = it)
                }
            }
            .toList()
            .toObservable()
            .map { Companion.createData(it) }
            .startWithSingle(Companion.createLoading())
            .onErrorResumeNext(DelayFunction<PostListViewModel>(scheduler))
            .onErrorReturn { onError(it) }
    //endregion

    private fun onError(error: Throwable): PostListViewModel =
        Companion.createError(getErrorMessage(error))
}
