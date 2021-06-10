package com.khamdan.presentation.scenes.userdetail

import com.khamdan.data.extensions.startWithSingle
import com.khamdan.domain.functions.DelayFunction
import com.khamdan.domain.model.UserDetailData
import com.khamdan.domain.usecases.GetListAlbum
import com.khamdan.domain.usecases.GetListPhoto
import com.khamdan.domain.usecases.GetUser
import com.khamdan.presentation.exception.ErrorMessageFactory
import com.khamdan.presentation.scenes.base.presenter.APresenter
import com.khamdan.presentation.scenes.userdetail.UserDetailViewModel.Companion
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class UserDetailPresenter
@Inject constructor(
    private val getUser: GetUser,
    private val getListAlbum: GetListAlbum,
    private val getListPhoto: GetListPhoto,
    private val router: UserDetailRouter,
    private val scheduler: Scheduler,
    errorMessageFactory: ErrorMessageFactory
) : APresenter<UserDetailView, UserDetailViewModel>(errorMessageFactory) {

    override fun attach(view: UserDetailView) {
        val loadPost = view.intentLoadData()
            .flatMap { userId -> loadUserDetail(userId) }

        val retryPost = view.intentErrorRetry()
            .flatMap { userId -> loadUserDetail(userId) }

        subscribeViewModel(view, loadPost, retryPost)

        view.openPhotoDetail()
            .subscribe { photo -> router.routeToPhotoDetail(photo.title, photo.url) }
            .addTo(composite)
    }

    //region USE CASES TO VIEW MODEL
    private fun loadUserDetail(userId: Int): Observable<UserDetailViewModel> =
        getUser.execute(userId).toObservable()
            .concatMap { user ->
                getListAlbum.execute(user.id).toObservable()
                    .flatMapIterable { it }
                    .flatMap { album ->
                        getListPhoto.execute(album.id).toObservable()
                            .map { photos -> album.copy(photos = photos) }
                    }
                    .toList()
                    .toObservable()
                    .map { albums ->
                        Companion.createData(UserDetailData(user, albums))
                    }
            }
            .startWithSingle(Companion.createLoading())
            .onErrorResumeNext(DelayFunction<UserDetailViewModel>(scheduler))
            .onErrorReturn { onError(it) }
    //endregion

    private fun onError(error: Throwable): UserDetailViewModel =
        Companion.createError(getErrorMessage(error))
}
