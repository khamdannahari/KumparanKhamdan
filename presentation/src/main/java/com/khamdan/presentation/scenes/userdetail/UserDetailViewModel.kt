package com.khamdan.presentation.scenes.userdetail

import com.khamdan.domain.model.PostDetailData
import com.khamdan.domain.model.UserDetailData
import com.khamdan.presentation.scenes.base.view.ContentState
import com.khamdan.presentation.scenes.base.view.ContentState.CONTENT
import com.khamdan.presentation.scenes.base.view.ContentState.ERROR
import com.khamdan.presentation.scenes.base.view.LoadingState
import com.khamdan.presentation.scenes.base.view.LoadingState.LOADING
import com.khamdan.presentation.scenes.base.view.LoadingState.RETRY

data class UserDetailViewModel(
    val loadingState: LoadingState = LoadingState.NONE,
    val contentState: ContentState = ContentState.NONE,
    val data: UserDetailData?= null,
    val errorMessage: String? = null,
    val snackMessage: String? = null
) {
    companion object {
        fun createLoading() = UserDetailViewModel(
            loadingState = LOADING,
            contentState = CONTENT
        )

        fun createRetryLoading() =
            UserDetailViewModel(loadingState = RETRY, contentState = ERROR)

        fun createData(data: UserDetailData) =
            UserDetailViewModel(contentState = CONTENT, data = data)

        fun createError(error: String) =
            UserDetailViewModel(contentState = ERROR, errorMessage = error)

        fun createSnack(snackMessage: String) =
            UserDetailViewModel(contentState = CONTENT, snackMessage = snackMessage)
    }
}