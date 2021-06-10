package com.khamdan.presentation.di.components

import android.app.Activity
import com.khamdan.presentation.di.PerActivity
import com.khamdan.presentation.di.modules.ActivityModule
import com.khamdan.presentation.scenes.photodetail.PhotoDetailFragment
import com.khamdan.presentation.scenes.postdetail.PostDetailFragment
import com.khamdan.presentation.scenes.postlist.PostListFragment
import com.khamdan.presentation.scenes.userdetail.UserDetailFragment
import dagger.BindsInstance
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): ActivityComponent
    }

    //region Inject
    fun inject(fragment: PostListFragment)
    fun inject(fragment: PostDetailFragment)
    fun inject(fragment: UserDetailFragment)
    fun inject(fragment: PhotoDetailFragment)
    //endregion

}
