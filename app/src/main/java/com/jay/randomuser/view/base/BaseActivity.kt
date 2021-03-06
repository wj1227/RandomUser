package com.jay.randomuser.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jay.randomuser.BR

abstract class BaseActivity<VDB: ViewDataBinding, VM: ViewModelType<*, *>>(
    @LayoutRes
    protected val layoutResId: Int
) : AppCompatActivity() {
    protected lateinit var viewDataBinding: VDB
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView<VDB>(this, layoutResId).apply {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.viewModel, viewModel)
        }

        initViewModelObserving()
    }

    abstract fun initViewModelObserving()
}