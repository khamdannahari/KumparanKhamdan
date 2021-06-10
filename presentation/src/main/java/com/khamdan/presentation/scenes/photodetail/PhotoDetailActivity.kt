package com.khamdan.presentation.scenes.photodetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.khamdan.presentation.R
import com.khamdan.presentation.extensions.addFragment
import com.khamdan.presentation.extensions.enableToolbar
import com.khamdan.presentation.extensions.getStringExtra
import com.khamdan.presentation.scenes.base.view.ABaseActivity

class PhotoDetailActivity : ABaseActivity(R.layout.activity_layout_to_load_fragment) {

    companion object {

        private const val EXTRA_PHOTO_NAME = "extra_photo_name"
        private const val EXTRA_PHOTO_URL = "extra_photo_url"

        fun newIntent(context: Context, photoName: String, photoUrl: String): Intent =
            Intent(context, PhotoDetailActivity::class.java).apply {
                putExtra(EXTRA_PHOTO_NAME, photoName)
                putExtra(EXTRA_PHOTO_URL, photoUrl)
            }
    }

    private val photoName: String by lazy { getStringExtra(EXTRA_PHOTO_NAME) }
    private val photoUrl: String by lazy { getStringExtra(EXTRA_PHOTO_URL) }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make sure this is before calling super.onCreate
        setTheme(R.style.Base_Theme)
        super.onCreate(savedInstanceState)
        initializeActivity(savedInstanceState)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        enableToolbar(true, photoName)
    }

    private fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(
                R.id.container,
                PhotoDetailFragment.newInstance(photoUrl)
            )
        }
    }
}
