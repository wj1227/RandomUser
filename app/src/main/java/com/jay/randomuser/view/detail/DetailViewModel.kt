package com.jay.randomuser.view.detail

import com.jay.randomuser.view.base.BaseViewModel
import com.jay.randomuser.view.base.ViewModelType

interface DetailViewModelType: ViewModelType<DetailViewModelType.Input, DetailViewModelType.Output> {
    interface Input {

    }

    interface Output {

    }
}


class DetailViewModel(

) : BaseViewModel(),DetailViewModelType, DetailViewModelType.Input, DetailViewModelType.Output {

    override val input: DetailViewModelType.Input
        get() = this

    override val output: DetailViewModelType.Output
        get() = this
}