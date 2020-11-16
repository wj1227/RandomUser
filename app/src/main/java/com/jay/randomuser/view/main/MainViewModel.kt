package com.jay.randomuser.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.randomuser.data.api.RemoteApi
import com.jay.randomuser.data.response.UserResponse
import com.jay.randomuser.view.base.BaseViewModel
import com.jay.randomuser.view.base.ViewModelType
import com.jay.randomuser.view.main.mapper.UserListMapper
import com.jay.randomuser.view.main.model.UserUiModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.*

interface MainViewModelType : ViewModelType<MainViewModelType.Input, MainViewModelType.Output> {
    interface Input {
        fun onGenderFilter()
        fun onScrollTop()
        fun onRefresh()
        fun onMaleClick()
        fun onFeMaleClick()
    }

    interface Output {
        val gender: LiveData<String>
        val scrollToTop: LiveData<Unit>
        val isRefresh: LiveData<Boolean>
        val users: LiveData<List<UserUiModel>>
        val genderFilter: LiveData<Unit>
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
    private val _userClickSubject: PublishSubject<UserResponse> = PublishSubject.create()
    private val _userSubject: BehaviorSubject<List<UserUiModel>> = BehaviorSubject.createDefault(emptyList())

    private val _userMapper: UserListMapper = UserListMapper(onClick = _userClickSubject)

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

    private val _genderFilter: MutableLiveData<Unit> = MutableLiveData()
    override val genderFilter: LiveData<Unit>
        get() = _genderFilter

    init {
        val seedGenerator = _onRefresh
            .startWith(Unit)
            .map { UUID.randomUUID().toString() }
            .share()

        val seedWithGender = Observable.combineLatest(
            seedGenerator,
            _genderSubject,
            BiFunction { seed: String, gender: String -> Pair(seed, gender) }
        )
            .replay(1).autoConnect()

        val randomUser = seedWithGender
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { showRefreshLoading(); showLoading() }
            .switchMapSingle { (seed, gender) ->
                api.getUsers(page = 0, results = RESULT_COUNT, seed = seed, gender = gender)
                    .subscribeOn(Schedulers.io())
            }
            .materialize()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { hideRefreshLoading(); hideLoading() }
            .share()

        randomUser.filter { it.isOnNext }
            .map { it.value }
            .map { it.results }
            .map(_userMapper::mapper)
            .subscribe(_userSubject::onNext)
            .let(compositeDisposable::add)

        _userSubject.observeOn(AndroidSchedulers.mainThread())
            .subscribe(_users::setValue)
            .let(compositeDisposable::add)

        _genderSubject.observeOn(AndroidSchedulers.mainThread())
            .subscribe(_gender::setValue)
            .let(compositeDisposable::add)

        _onGenderClick.observeOn(AndroidSchedulers.mainThread())
            .subscribe(_genderFilter::setValue)
            .let(compositeDisposable::add)

    }


    private fun showRefreshLoading() {
        _isRefresh.value = true
    }

    private fun hideRefreshLoading() {
        _isRefresh.value = false
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

    override fun onMaleClick() {
        _genderSubject.onNext("male")
    }

    override fun onFeMaleClick() {
        _genderSubject.onNext("female")
    }

    companion object {
        const val RESULT_COUNT = 20
    }

}