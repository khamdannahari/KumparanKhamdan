package com.khamdan.presentation.scenes.postlist

import androidx.appcompat.app.AppCompatActivity
import com.khamdan.presentation.scenes.postdetail.PostDetailActivity
import javax.inject.Inject

class PostListRouter
@Inject internal constructor(private val activity: AppCompatActivity) {

    fun routeToPostDetail(postId: Int, userId: Int) {
        activity.startActivity(
            PostDetailActivity.newIntent(activity.applicationContext, postId, userId)
        )
    }
}