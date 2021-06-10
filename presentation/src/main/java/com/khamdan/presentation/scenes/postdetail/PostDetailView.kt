package com.khamdan.presentation.scenes.postdetail

import com.khamdan.presentation.scenes.base.view.LoadDataView
import io.reactivex.rxjava3.core.Observable

interface PostDetailView : LoadDataView<PostDetailViewModel> {

    fun intentLoadData(): Observable<Pair<Int,Int>>

    fun intentErrorRetry(): Observable<Pair<Int,Int>>

    fun openUserDetail(): Observable<Int>

}