package com.khamdan.presentation.scenes.photodetail

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.khamdan.presentation.R
import com.khamdan.presentation.extensions.build
import com.khamdan.presentation.extensions.getStringArg
import com.khamdan.presentation.scenes.base.view.ABaseDataFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_detail_fragment.iv_photo

class PhotoDetailFragment : ABaseDataFragment(R.layout.photo_detail_fragment) {

    companion object {

        private const val ARGS_PHOTO_URL = "args_photo_url"

        fun newInstance(photoUrl: String): PhotoDetailFragment =
            PhotoDetailFragment().build {
                putString(ARGS_PHOTO_URL, photoUrl)
            }
    }

    private val photoUrl: String by lazy { getStringArg(ARGS_PHOTO_URL) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        Picasso.get().load(photoUrl).into(iv_photo)
//        val zoomAnimation = AnimationUtils.loadAnimation(context, R.anim.zoom)
//        iv_photo.startAnimation(zoomAnimation)
    }
}