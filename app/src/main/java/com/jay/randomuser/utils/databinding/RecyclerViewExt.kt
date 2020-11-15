package com.jay.randomuser.utils.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jay.randomuser.utils.diff.Identifiable
import com.jay.randomuser.view.base.BaseRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("list")
fun <T: Identifiable> RecyclerView.onBindList(list: List<T>?) {
    if (this.adapter is BaseRecyclerViewAdapter<*>) {
        val adapter = this.adapter as BaseRecyclerViewAdapter<T>
        adapter.submitList(list)
    }
}