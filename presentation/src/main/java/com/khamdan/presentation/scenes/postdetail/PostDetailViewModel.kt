package com.khamdan.presentation.scenes.postdetail

import com.khamdan.domain.model.PostDetailData
import com.khamdan.presentation.scenes.base.view.ContentState
import com.khamdan.presentation.scenes.base.view.ContentState.CONTENT
import com.khamdan.presentation.scenes.base.view.ContentState.ERROR
import com.khamdan.presentation.scenes.base.view.LoadingState
import com.khamdan.presentation.scenes.base.view.LoadingState.LOADING
import com.khamdan.presentation.scenes.base.view.LoadingState.RETRY

data class PostDetailViewModel(
    val loadingState: LoadingState = LoadingState.NONE,
    val contentState: ContentState = ContentState.NONE,
    val data: PostDetailData?= null,
    val errorMessage: String? = null,
    val snackMessage: String? = null
) {
    companion object {
        fun createLoading() = PostDetailViewModel(
            loadingState = LOADING,
            contentState = CONTENT
        )

        fun createRetryLoading() =
            PostDetailViewModel(loadingState = RETRY, contentState = ERROR)

        fun createData(data: PostDetailData) =
            PostDetailViewModel(contentState = CONTENT, data = data)

        fun createError(error: String) =
            PostDetailViewModel(contentState = ERROR, errorMessage = error)

        fun createSnack(snackMessage: String) =
            PostDetailViewModel(contentState = CONTENT, snackMessage = snackMessage)
    }
}