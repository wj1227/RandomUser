package com.jay.randomuser.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.randomuser.data.response.UserResponse
import com.jay.randomuser.utils.lifecycle.SingleData
import com.jay.randomuser.view.base.BaseViewModel
import com.jay.randomuser.view.base.ViewModelType
import com.jay.randomuser.view.detail.mapper.UserDetailMapper
import com.jay.randomuser.view.detail.model.UserDetailModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

interface DetailViewModelType: ViewModelType<DetailViewModelType.Input, DetailViewModelType.Output> {
    interface Input {
        fun cellPhoneClick()
        fun phoneClick()
        fun emailClick()
    }

    interface Output {
        val user: LiveData<UserDetailModel>
        val email: LiveData<SingleData<String>>
        val cellPhone: LiveData<SingleData<String>>
        val phone: LiveData<SingleData<String>>
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

    private val _emailSubject: PublishSubject<Unit> = PublishSubject.create()
    private val _email: MutableLiveData<SingleData<String>> = MutableLiveData()
    override val email: LiveData<SingleData<String>>
        get() = _email

    private val _cellPhoneSubject: PublishSubject<Unit> = PublishSubject.create()
    private val _cellPhone: MutableLiveData<SingleData<String>> = MutableLiveData()
    override val cellPhone: LiveData<SingleData<String>>
        get() = _cellPhone

    private val _phoneSubject: PublishSubject<Unit> = PublishSubject.create()
    private val _phone: MutableLiveData<SingleData<String>> = MutableLiveData()
    override val phone: LiveData<SingleData<String>>
        get() = _phone

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

        _cellPhoneSubject.withLatestFrom(user, BiFunction { _: Unit, user: UserResponse -> user })
            .map { it.cell.trim() }
            .map { SingleData(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(_cellPhone::setValue)
            .let(compositeDisposable::add)

        _phoneSubject.withLatestFrom(user, BiFunction { _: Unit, user: UserResponse -> user })
            .map { it.phone.trim() }
            .map { SingleData(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(_phone::setValue)
            .let(compositeDisposable::add)

        _emailSubject.withLatestFrom(user, BiFunction { _: Unit, user: UserResponse -> user })
            .map { it.email}
            .map { SingleData(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(_email::setValue)
            .let(compositeDisposable::add)
    }

    override fun cellPhoneClick() {
        _cellPhoneSubject.onNext(Unit)
    }

    override fun phoneClick() {
        _phoneSubject.onNext(Unit)
    }

    override fun emailClick() {
        _emailSubject.onNext(Unit)
    }
}