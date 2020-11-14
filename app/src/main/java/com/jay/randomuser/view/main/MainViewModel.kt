package com.jay.randomuser.view.main

import com.jay.randomuser.view.base.BaseViewModel
import com.jay.randomuser.view.base.ViewModelType

interface MainViewModelType : ViewModelType<MainViewModelType.Input, MainViewModelType.Output> {
    interface Input {

    }

    interface Output {

    }
}

class MainViewModel()
    : BaseViewModel(), MainViewModelType, MainViewModelType.Input, MainViewModelType.Output {

    override val input: MainViewModelType.Input
        get() = this
    override val output: MainViewModelType.Output
        get() = this

}