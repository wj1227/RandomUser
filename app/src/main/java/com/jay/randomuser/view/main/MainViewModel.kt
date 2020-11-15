package com.jay.randomuser.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.randomuser.data.api.RemoteApi
import com.jay.randomuser.view.base.BaseViewModel
import com.jay.randomuser.view.base.ViewModelType
import com.jay.randomuser.view.main.model.UserUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

interface MainViewModelType : ViewModelType<MainViewModelType.Input, MainViewModelType.Output> {
    interface Input {
        fun onGenderFilter()
        fun onScrollTop()
        fun onRefresh()
    }

    interface Output {
        val gender: LiveData<String>
        val scrollToTop: LiveData<Unit>
        val isRefresh: LiveData<Boolean>
        val users: LiveData<List<UserUiModel>>
    }
}

class MainViewModel(
    private val api: RemoteApi
) : BaseViewModel(), MainViewModelType, MainViewModelType.Input, MainViewModelType.Output {
    private val TAG = javaClass.simpleName

    override val input: MainViewModelType.Input
        get() = this
    override val output: MainViewModelType.Output
        get() = this

    private val _onGenderClick: PublishSubject<Unit> = PublishSubject.create()
    private val _genderSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("male")
    private val _onRefresh: PublishSubject<Unit> = PublishSubject.create()
    private val _scrollToTopSubject: PublishSubject<Unit> = PublishSubject.create()

    private val _isRefresh: MutableLiveData<Boolean> = MutableLiveData()
    override val isRefresh: LiveData<Boolean>
        get() = _isRefresh

    private val _scrollToTop: MutableLiveData<Unit> = MutableLiveData()
    override val scrollToTop: LiveData<Unit>
        get() = _scrollToTop

    private val _gender: MutableLiveData<String> = MutableLiveData()
    override val gender: LiveData<String>
        get() = _gender

    private val _users: MutableLiveData<List<UserUiModel>> = MutableLiveData()
    override val users: LiveData<List<UserUiModel>>
        get() = _users

    init {

        api.getUsers(1, 3, "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "${it.results.size}, $it: ")
            }, {
                Log.d(TAG, "$it: ")
            }).addTo(compositeDisposable)
    }

    override fun onGenderFilter() {
        _onGenderClick.onNext(Unit)
    }

    override fun onScrollTop() {
        _scrollToTopSubject.onNext(Unit)
    }

    override fun onRefresh() {
        _onRefresh.onNext(Unit)
    }
}