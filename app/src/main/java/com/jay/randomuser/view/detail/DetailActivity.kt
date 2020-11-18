package com.jay.randomuser.view.detail

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.jay.randomuser.R
import com.jay.randomuser.data.response.UserResponse
import com.jay.randomuser.databinding.ActivityDetailBinding
import com.jay.randomuser.view.base.ActivityLauncher
import com.jay.randomuser.view.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(
    layoutResId = R.layout.activity_detail
) {

    override val viewModel: DetailViewModel by viewModel {
        parametersOf(intent.getParcelableExtra(EXTRA_USER))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initViewModelObserving() {
        with(viewModel) {
            output.user.observe(this@DetailActivity, Observer { data ->
                this@DetailActivity.title = data.name
            })
        }
    }

    companion object : ActivityLauncher<DetailActivity> {
        override val activityClass = DetailActivity::class

        private const val EXTRA_USER = "extraUser"

        fun startActivity(context: Context?, user: UserResponse) {
            startActivityWithExtra(context, bundleOf(
                EXTRA_USER to user
            ))
        }
    }
}