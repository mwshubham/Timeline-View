package com.github.vipulasri.timelineview.sample.example

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun inflateView(@LayoutRes layoutResId: Int, parent: ViewGroup, attachToRoot: Boolean): View {
    return LayoutInflater.from(parent.context).inflate(layoutResId, parent, attachToRoot)
}

inline fun <T : Any, R> whenNotNull(input: T?, callback: (T) -> R): R? {
    return input?.let(callback)
}

infix fun <T> Boolean.then(param: T): T? = if (this) param else null

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Float.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
