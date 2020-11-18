package com.jay.randomuser.utils.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jay.randomuser.utils.diff.Identifiable
import com.jay.randomuser.utils.pagination.EndlessRecyclerViewScrollListener
import com.jay.randomuser.view.base.BaseRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("list")
fun <T: Identifiable> RecyclerView.onBindList(list: List<T>?) {
    if (this.adapter is BaseRecyclerViewAdapter<*>) {
        val adapter = this.adapter as BaseRecyclerViewAdapter<T>
        adapter.submitList(list)
    }
}

@BindingAdapter("onLoadMore")
fun RecyclerView.bindOnLoadMore(listener: OnLoadMoreListener) {
    this.addOnScrollListener(object : EndlessRecyclerViewScrollListener() {
        override fun onLoadMore(view: RecyclerView) {
            listener.onLoadMore()
        }
    })
}

@FunctionalInterface
interface OnLoadMoreListener {
    fun onLoadMore()
}