package com.jay.randomuser.view.main

import android.os.Bundle
import com.jay.randomuser.R
import com.jay.randomuser.databinding.ActivityMainBinding
import com.jay.randomuser.view.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    layoutResId = R.layout.activity_main
) {

    override val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModelObserving()
        initAdapter()
    }

    private fun initViewModelObserving() {
        with(viewModel) {

        }
    }

    private fun initAdapter() {
        adapter = MainAdapter()

        viewDataBinding.rvResult.adapter = adapter
    }
}