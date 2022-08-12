package com.aa.mvivm.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setTextWith")
fun TextView.setTextWith(value: Int?) {
    text = value?.toString() ?: ""
}

@BindingAdapter("onDefaultLongClick")
fun View.onDefaultLongClick(listener: View.OnClickListener?) {
    listener?.also {
        setOnLongClickListener {
            listener.onClick(it)
            true
        }
    }
}
