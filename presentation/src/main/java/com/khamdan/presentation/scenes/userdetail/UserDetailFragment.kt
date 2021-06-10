package com.khamdan.presentation.scenes.userdetail

import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding4.view.clicks
import com.khamdan.data.helper.TimberWrapper
import com.khamdan.domain.model.Photo
import com.khamdan.domain.model.UserDetailData
import com.khamdan.presentation.R
import com.khamdan.presentation.extensions.build
import com.khamdan.presentation.extensions.getIntArg
import com.khamdan.presentation.scenes.base.view.ABaseDataFragment
import com.khamdan.presentation.scenes.base.view.ContentState
import com.khamdan.presentation.scenes.base.view.LoadingState
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.error_layout.btnErrorRetry
import kotlinx.android.synthetic.main.user_detail_fragment.content
import kotlinx.android.synthetic.main.user_detail_fragment.rv_album
import kotlinx.android.synthetic.main.user_detail_fragment.tv_user_address
import kotlinx.android.synthetic.main.user_detail_fragment.tv_user_company
import kotlinx.android.synthetic.main.user_detail_fragment.tv_user_email
import kotlinx.android.synthetic.main.user_detail_fragment.tv_user_name
import javax.inject.Inject

class UserDetailFragment : ABaseDataFragment(R.layout.user_detail_fragment), UserDetailView {

    companion object {

        private const val ARGS_USER_ID = "args_user_id"

        fun newInstance(userId: Int): UserDetailFragment =
            UserDetailFragment().build {
                putInt(ARGS_USER_ID, userId)
            }
    }

    @Inject
    lateinit var presenter: UserDetailPresenter

    private val userId: Int by lazy { getIntArg(ARGS_USER_ID) }

    private val albumAdapter = AlbumAdapter()

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
        rv_album.setHasFixedSize(true)
        rv_album.adapter = albumAdapter
    }

    //region INTENTS
    override fun intentLoadData(): Observable<Int> =
        Observable.just(userId)

    override fun intentErrorRetry(): Observable<Int> =
        btnErrorRetry.clicks().map { userId }

    override fun openPhotoDetail(): Observable<Photo> =
        albumAdapter.photoClickIntent.map { it }
    //endregion

    //region RENDER
    override fun render(viewModel: UserDetailViewModel) {
        TimberWrapper.d { "render: $viewModel" }

        showLoading(viewModel.loadingState == LoadingState.LOADING)
        showRetryLoading(viewModel.loadingState == LoadingState.RETRY)
        showContent(content, viewModel.contentState == ContentState.CONTENT)
        showError(viewModel.contentState == ContentState.ERROR)

        renderData(viewModel.data)
        renderError(viewModel.errorMessage)
        renderSnack(viewModel.snackMessage)
    }

    private fun renderData(userDetailData: UserDetailData?) {
        userDetailData?.let { detailData ->
            tv_user_name.text = detailData.user?.name ?: ""
            tv_user_email.text = detailData.user?.email ?: ""
            tv_user_address.text = detailData.user?.address?.street ?: ""
            tv_user_company.text = detailData.user?.company?.name ?: ""
            albumAdapter.data = detailData.albums.toMutableList()
            rv_album.scrollToPosition(0)
        }
    }
    //endregion

}
