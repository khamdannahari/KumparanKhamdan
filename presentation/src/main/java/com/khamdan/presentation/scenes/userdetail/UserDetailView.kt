package com.khamdan.presentation.scenes.userdetail

import com.khamdan.domain.model.Photo
import com.khamdan.presentation.scenes.base.view.LoadDataView
import io.reactivex.rxjava3.core.Observable

interface UserDetailView : LoadDataView<UserDetailViewModel> {

    fun intentLoadData(): Observable<Int>

    fun intentErrorRetry(): Observable<Int>

    fun openPhotoDetail(): Observable<Photo>

}