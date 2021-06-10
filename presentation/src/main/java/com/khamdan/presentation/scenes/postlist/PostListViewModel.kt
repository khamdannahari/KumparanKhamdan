package com.khamdan.presentation.scenes.postlist

import com.khamdan.domain.model.Post
import com.khamdan.presentation.scenes.base.view.ContentState
import com.khamdan.presentation.scenes.base.view.ContentState.CONTENT
import com.khamdan.presentation.scenes.base.view.ContentState.ERROR
import com.khamdan.presentation.scenes.base.view.LoadingState
import com.khamdan.presentation.scenes.base.view.LoadingState.LOADING
import com.khamdan.presentation.scenes.base.view.LoadingState.RETRY

data class PostListViewModel(
    val loadingState: LoadingState = LoadingState.NONE,
    val contentState: ContentState = ContentState.NONE,
    val data: List<Post>? = null,
    val errorMessage: String? = null,
    val snackMessage: String? = null
) {
    companion object {
        fun createLoading() = PostListViewModel(
            loadingState = LOADING,
            contentState = CONTENT
        )

        fun createRetryLoading() =
            PostListViewModel(loadingState = RETRY, contentState = ERROR)

        fun createData(data: List<Post>) =
            PostListViewModel(contentState = CONTENT, data = data)

        fun createError(error: String) =
            PostListViewModel(contentState = ERROR, errorMessage = error)

        fun createSnack(snackMessage: String) =
            PostListViewModel(contentState = CONTENT, snackMessage = snackMessage)
    }
}