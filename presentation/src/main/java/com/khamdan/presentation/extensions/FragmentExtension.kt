package com.khamdan.presentation.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <T : Fragment> T.build(args: Bundle.() -> Unit): T =
    apply { arguments = Bundle().apply(args) }

fun Fragment.getIntArg(key: String): Int = arguments!!.getInt(key)

fun Fragment.getLongArg(key: String): Long = arguments!!.getLong(key)

fun Fragment.getBooleanArg(key: String): Boolean = arguments!!.getBoolean(key)

fun Fragment.getStringArg(key: String): String = arguments!!.getString(key)!!