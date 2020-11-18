package com.jay.randomuser.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jay.randomuser.R
import com.jay.randomuser.databinding.ActivityDetailBinding
import com.jay.randomuser.view.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(
    layoutResId = R.layout.activity_detail
) {
    override val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initViewModelObserving() {
        with(viewModel) {

        }
    }
}