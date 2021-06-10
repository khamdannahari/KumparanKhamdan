package com.khamdan.presentation.scenes.postlist

import com.khamdan.domain.model.Post
import com.khamdan.presentation.scenes.base.view.LoadDataView

import io.reactivex.rxjava3.core.Observable

interface PostListView : LoadDataView<PostListViewModel> {

    fun intentLoadData(): Observable<Unit>

    fun intentRefreshData(): Observable<Unit>

    fun intentErrorRetry(): Observable<Unit>

    fun openPostDetail(): Observable<Post>

}