package com.jay.randomuser.view.main

import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.os.Bundle
import androidx.lifecycle.Observer
import com.jay.randomuser.R
import com.jay.randomuser.databinding.ActivityMainBinding
import com.jay.randomuser.utils.ext.showSafely
import com.jay.randomuser.view.base.BaseActivity
import com.jay.randomuser.view.detail.DetailActivity
import com.jay.randomuser.view.main.genderselect.GenderSelectFragment
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

    override fun initViewModelObserving() {
        with(viewModel) {
            output.genderFilter.observe(this@MainActivity, Observer {
                GenderSelectFragment.newInstance()
                    .showSafely(supportFragmentManager, GenderSelectFragment.tag)
            })
            output.scrollToTop.observe(this@MainActivity, Observer {
                viewDataBinding.rvResult.layoutManager?.smoothScrollToPosition(
                    viewDataBinding.rvResult,
                    null,
                    0
                )
            })
            startDetail.observe(this@MainActivity, Observer { data ->
                DetailActivity.startActivity(this@MainActivity, data)
//                data.consume {
//                    DetailActivity.startActivity(this@MainActivity, it)
//                }
            })
        }
    }

    private fun initAdapter() {
        adapter = MainAdapter()

        viewDataBinding.rvResult.adapter = adapter
    }
}