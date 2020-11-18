package com.jay.randomuser.view.detail

import android.util.Log
import com.jay.randomuser.data.response.UserResponse
import com.jay.randomuser.view.base.BaseViewModel
import com.jay.randomuser.view.base.ViewModelType

interface DetailViewModelType: ViewModelType<DetailViewModelType.Input, DetailViewModelType.Output> {
    interface Input {

    }

    interface Output {

    }
}


class DetailViewModel(
    user: UserResponse
) : BaseViewModel(),DetailViewModelType, DetailViewModelType.Input, DetailViewModelType.Output {
    private val TAG = javaClass.simpleName
    override val input: DetailViewModelType.Input
        get() = this

    override val output: DetailViewModelType.Output
        get() = this

    init {
        Log.d(TAG, "user: $user")
    }
}