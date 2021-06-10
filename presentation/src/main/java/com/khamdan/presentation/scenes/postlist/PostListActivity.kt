package com.khamdan.presentation.scenes.postlist

import android.os.Bundle
import com.khamdan.presentation.R
import com.khamdan.presentation.R.string
import com.khamdan.presentation.extensions.addFragment
import com.khamdan.presentation.extensions.enableToolbar
import com.khamdan.presentation.scenes.base.view.ABaseActivity

class PostListActivity : ABaseActivity(R.layout.activity_layout_to_load_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make sure this is before calling super.onCreate
        setTheme(R.style.Base_Theme)
        super.onCreate(savedInstanceState)
        initializeActivity(savedInstanceState)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        enableToolbar(false, getString(string.app_name))
    }

    private fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(R.id.container, PostListFragment.newInstance())
        }
    }
}
