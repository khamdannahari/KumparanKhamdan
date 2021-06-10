package com.khamdan.presentation.scenes.userdetail

import androidx.appcompat.app.AppCompatActivity
import com.khamdan.presentation.scenes.photodetail.PhotoDetailActivity
import javax.inject.Inject

class UserDetailRouter
@Inject internal constructor(private val activity: AppCompatActivity) {

    fun routeToPhotoDetail(photoName: String, photoUrl: String) {
        activity.startActivity(
            PhotoDetailActivity.newIntent(
                activity.applicationContext,
                photoName,
                photoUrl
            )
        )
    }
}