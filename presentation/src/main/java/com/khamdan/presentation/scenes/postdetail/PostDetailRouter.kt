package com.khamdan.presentation.scenes.postdetail

import androidx.appcompat.app.AppCompatActivity
import com.khamdan.presentation.scenes.userdetail.UserDetailActivity
import javax.inject.Inject

class PostDetailRouter
@Inject internal constructor(private val activity: AppCompatActivity) {

    fun routeToUserDetail(userId: Int) {
        activity.startActivity(
            UserDetailActivity.newIntent(
                activity.applicationContext,
                userId
            )
        )
    }
}