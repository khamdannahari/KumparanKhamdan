package com.khamdan.presentation.scenes.base.view

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.khamdan.presentation.AndroidApplication
import com.khamdan.presentation.di.components.ActivityComponent
import com.khamdan.presentation.di.components.ApplicationComponent

abstract class ABaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected val appComponent: ApplicationComponent by lazy { (requireActivity().application as AndroidApplication).appComponent }

    protected val activityComponent: ActivityComponent by lazy { (activity as ABaseActivity).activityComponent }

    override fun getContext(): Context = requireContext()
}
