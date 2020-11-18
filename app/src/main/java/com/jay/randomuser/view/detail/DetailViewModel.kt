package com.jay.randomuser.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.randomuser.data.response.UserResponse
import com.jay.randomuser.view.base.BaseViewModel
import com.jay.randomuser.view.base.ViewModelType
import com.jay.randomuser.view.detail.mapper.UserDetailMapper
import com.jay.randomuser.view.detail.model.UserDetailModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

interface DetailViewModelType: ViewModelType<DetailViewModelType.Input, DetailViewModelType.Output> {
    interface Input {

    }

    interface Output {
        val user: LiveData<UserDetailModel>
    }
}


class DetailViewModel(
    user: UserResponse
) : BaseViewModel(),DetailViewModelType, DetailViewModelType.Input, DetailViewModelType.Output {
    override val input: DetailViewModelType.Input
        get() = this

    override val output: DetailViewModelType.Output
        get() = this

    private val _userSubject: BehaviorSubject<UserDetailModel> = BehaviorSubject.create()
    private val _user: MutableLiveData<UserDetailModel> = MutableLiveData()
    override val user: LiveData<UserDetailModel>
        get() = _user

    init {
        val user = Observable.just(user)
            .replay(1)
            .autoConnect()

        user.map(UserDetailMapper::mapper)
            .subscribe(_userSubject::onNext)
            .let(compositeDisposable::add)

        _userSubject.observeOn(AndroidSchedulers.mainThread())
            .subscribe(_user::setValue)
            .let(compositeDisposable::add)
    }
}