package com.example.movieapp.ext

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide


/**
 * Created by ali samy Mohamed on 4/4/2020.
 * alisamymohamed@outlook.com
 */


fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}

fun <T : ViewDataBinding> View.bind() = DataBindingUtil.bind<T>(this) as T




/**
 * Remove the view (visibility = View.GONE)
 */
fun View.remove() : View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}


val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)


