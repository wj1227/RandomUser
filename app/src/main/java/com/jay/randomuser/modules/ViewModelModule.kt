package com.jay.randomuser.modules

import com.jay.randomuser.data.response.UserResponse
import com.jay.randomuser.view.detail.DetailViewModel
import com.jay.randomuser.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }

    viewModel { (user: UserResponse) ->
        DetailViewModel(user)
    }
}