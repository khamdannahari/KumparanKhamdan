package com.khamdan.presentation.scenes.userdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.khamdan.presentation.R
import com.khamdan.presentation.extensions.addFragment
import com.khamdan.presentation.extensions.enableToolbar
import com.khamdan.presentation.extensions.getIntExtra
import com.khamdan.presentation.extensions.getStringExtra
import com.khamdan.presentation.scenes.base.view.ABaseActivity

class UserDetailActivity : ABaseActivity(R.layout.activity_layout_to_load_fragment) {

    companion object {

        private const val EXTRA_USER_ID = "extra_user_id"

        fun newIntent(context: Context, userId: Int): Intent =
            Intent(context, UserDetailActivity::class.java).apply {
                putExtra(EXTRA_USER_ID, userId)
            }
    }

    private val userId: Int by lazy { getIntExtra(EXTRA_USER_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make sure this is before calling super.onCreate
        setTheme(R.style.Base_Theme)
        super.onCreate(savedInstanceState)
        initializeActivity(savedInstanceState)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        enableToolbar(true, getString(R.string.user_detail))
    }

    private fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(R.id.container, UserDetailFragment.newInstance(userId))
        }
    }
}
