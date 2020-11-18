package com.jay.randomuser.utils.databinding

import android.widget.RadioGroup
import androidx.core.view.children
import androidx.databinding.BindingAdapter

@BindingAdapter("checkedRadioWithTag")
fun <T> RadioGroup.bindCheckedRadio(tag: T?) {
    val selectButton = this.children.filter { childButton ->
        childButton.tag == tag
    }.firstOrNull()

    if (selectButton == null) {
        this.clearCheck()
    } else {
        this.check(selectButton.id)
    }
}