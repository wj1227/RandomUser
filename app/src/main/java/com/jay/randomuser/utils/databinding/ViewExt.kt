package com.jay.randomuser.utils.databinding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onClick")
fun View.bindOnClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}