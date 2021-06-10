package com.khamdan.presentation.scenes.postdetail

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import com.jakewharton.rxbinding4.view.clicks
import com.khamdan.data.helper.TimberWrapper
import com.khamdan.domain.model.PostDetailData
import com.khamdan.presentation.R
import com.khamdan.presentation.extensions.build
import com.khamdan.presentation.extensions.getIntArg
import com.khamdan.presentation.scenes.base.view.ABaseDataFragment
import com.khamdan.presentation.scenes.base.view.ContentState
import com.khamdan.presentation.scenes.base.view.LoadingState
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.error_layout.btnErrorRetry
import kotlinx.android.synthetic.main.post_detail_fragment.*
import javax.inject.Inject

class PostDetailFragment : ABaseDataFragment(R.layout.post_detail_fragment), PostDetailView {

    companion object {

        private const val ARGS_POST_ID = "args_post_id"
        private const val ARGS_USER_ID = "args_user_id"

        fun newInstance(postId: Int, userId: Int): PostDetailFragment =
            PostDetailFragment().build {
                putInt(ARGS_POST_ID, postId)
                putInt(ARGS_USER_ID, userId)
            }
    }

    @Inject
    lateinit var presenter: PostDetailPresenter

    private val postId: Int by lazy { getIntArg(ARGS_POST_ID) }
    private val userId: Int by lazy { getIntArg(ARGS_USER_ID) }

    private val commentAdapter = CommentAdapter()

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
        rv_comment.setHasFixedSize(true)
        rv_comment.adapter = commentAdapter
    }

    //region INTENTS
    override fun intentLoadData(): Observable<Pair<Int, Int>> =
        Observable.just(Pair(postId, userId))

    override fun intentErrorRetry(): Observable<Pair<Int, Int>> =
        btnErrorRetry.clicks().map { Pair(postId, userId) }

    override fun openUserDetail(): Observable<Int> =
        tv_user_name.clicks().map { userId }
    //endregion

    //region RENDER
    override fun render(viewModel: PostDetailViewModel) {
        TimberWrapper.d { "render: $viewModel" }

        showLoading(viewModel.loadingState == LoadingState.LOADING)
        showRetryLoading(viewModel.loadingState == LoadingState.RETRY)
        showContent(content, viewModel.contentState == ContentState.CONTENT)
        showError(viewModel.contentState == ContentState.ERROR)

        renderData(viewModel.data)
        renderError(viewModel.errorMessage)
        renderSnack(viewModel.snackMessage)
    }

    private fun renderData(postDetailData: PostDetailData?) {
        postDetailData?.let { detailData ->
            tv_user_name.text = detailData.user.name.underlineStyle()
            tv_post_title.text = detailData.post.title
            tv_post_body.text = detailData.post.body
            commentAdapter.data = detailData.comments.toMutableList()
            rv_comment.scrollToPosition(0)
        }
    }

    private fun String.underlineStyle() : SpannableString {
        return SpannableString(this).apply {
            setSpan(UnderlineSpan(), 0, length, 0)
        }
    }
    //endregion

}
