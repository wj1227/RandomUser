package com.jay.randomuser.utils.databinding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.bindOnRefresh(listener: SwipeRefreshLayout.OnRefreshListener) {
    this.setOnRefreshListener(listener)
}

@BindingAdapter("isRefresh")
fun SwipeRefreshLayout.bindIsRefresh(isRefreshing: Boolean) {
    this.isRefreshing = isRefreshing
}