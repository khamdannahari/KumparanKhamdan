package com.khamdan.presentation.scenes.postlist

import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding4.swiperefreshlayout.refreshes
import com.jakewharton.rxbinding4.view.clicks
import com.khamdan.data.helper.TimberWrapper
import com.khamdan.domain.model.Post
import com.khamdan.presentation.R
import com.khamdan.presentation.scenes.base.view.ABaseDataFragment
import com.khamdan.presentation.scenes.base.view.ContentState
import com.khamdan.presentation.scenes.base.view.LoadingState
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.post_list_fragment.*
import javax.inject.Inject

class PostListFragment : ABaseDataFragment(R.layout.post_list_fragment), PostListView {

    companion object {
        fun newInstance(): PostListFragment = PostListFragment()
    }

    @Inject
    lateinit var presenter: PostListPresenter

    private val postAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    private fun initView() {
        rv_post.setHasFixedSize(true)
        rv_post.adapter = postAdapter
    }

    //region INTENTS
    override fun intentLoadData(): Observable<Unit> =
        Observable.just(Unit)

    override fun intentRefreshData(): Observable<Unit> =
        swipe_refresh_layout.refreshes()

    override fun intentErrorRetry(): Observable<Unit> =
        btnErrorRetry.clicks()

    override fun openPostDetail(): Observable<Post> =
        postAdapter.postClickIntent
    //endregion

    //region RENDER
    override fun render(viewModel: PostListViewModel) {
        TimberWrapper.d { "render: $viewModel" }

        showLoading(viewModel.loadingState == LoadingState.LOADING)
        showRefreshingLoading(swipe_refresh_layout, false)
        showRetryLoading(viewModel.loadingState == LoadingState.RETRY)
        showContent(content, viewModel.contentState == ContentState.CONTENT)
        showError(viewModel.contentState == ContentState.ERROR)

        renderData(viewModel.data)
        renderError(viewModel.errorMessage)
        renderSnack(viewModel.snackMessage)
    }

    private fun renderData(postList: List<Post>?) {
        postList?.also {
            postAdapter.data = it.toMutableList()
            rv_post.scrollToPosition(0)
        }
    }
    //endregion

}
