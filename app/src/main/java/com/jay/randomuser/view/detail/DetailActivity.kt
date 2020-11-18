package com.jay.randomuser.view.detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import com.jay.randomuser.R
import com.jay.randomuser.data.response.UserResponse
import com.jay.randomuser.databinding.ActivityDetailBinding
import com.jay.randomuser.view.base.ActivityLauncher
import com.jay.randomuser.view.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.reflect.KClass

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(
    layoutResId = R.layout.activity_detail
) {
    private val TAG = javaClass.simpleName

    override val viewModel: DetailViewModel by viewModel {
        parametersOf(intent.getParcelableExtra(EXTRA_USER))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
        finish()
    }

    override fun initViewModelObserving() {
        with(viewModel) {

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