package com.khamdan.presentation.scenes.postdetail

import com.khamdan.data.extensions.startWithSingle
import com.khamdan.domain.functions.DelayFunction
import com.khamdan.domain.model.PostDetailData
import com.khamdan.domain.usecases.GetListComment
import com.khamdan.domain.usecases.GetPost
import com.khamdan.domain.usecases.GetUser
import com.khamdan.presentation.exception.ErrorMessageFactory
import com.khamdan.presentation.scenes.base.presenter.APresenter
import com.khamdan.presentation.scenes.postdetail.PostDetailViewModel.Companion
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class PostDetailPresenter
@Inject constructor(
    private val getPost: GetPost,
    private val getUser: GetUser,
    private val getListComment: GetListComment,
    private val router: PostDetailRouter,
    private val scheduler: Scheduler,
    errorMessageFactory: ErrorMessageFactory
) : APresenter<PostDetailView, PostDetailViewModel>(errorMessageFactory) {

    override fun attach(view: PostDetailView) {
        val loadPost = view.intentLoadData()
            .flatMap { postUserPair -> loadPostDetail(postUserPair.first, postUserPair.second) }
//
        val retryPost = view.intentErrorRetry()
            .flatMap { postUserPair -> loadPostDetail(postUserPair.first, postUserPair.second) }

        subscribeViewModel(view, loadPost, retryPost)

        view.openUserDetail()
            .subscribe { userId -> router.routeToUserDetail(userId) }
            .addTo(composite)
    }

    //region USE CASES TO VIEW MODEL
    private fun loadPostDetail(postId: Int, userId: Int): Observable<PostDetailViewModel> =
        Observables.combineLatest(
            getPost.execute(postId).toObservable(),
            getUser.execute(userId).toObservable()
        ) { post, user ->
            Pair(post, user)
        }
            .concatMap { postUserPair ->
                getListComment.execute(postUserPair.first.id).toObservable().map { comments ->
                    Companion.createData(
                        PostDetailData(
                            post = postUserPair.first,
                            user = postUserPair.second,
                            comments = comments
                        )
                    )
                }
            }
            .startWithSingle(Companion.createLoading())
            .onErrorResumeNext(DelayFunction<PostDetailViewModel>(scheduler))
            .onErrorReturn { onError(it) }

    //endregion

    private fun onError(error: Throwable): PostDetailViewModel =
        Companion.createError(getErrorMessage(error))
}
