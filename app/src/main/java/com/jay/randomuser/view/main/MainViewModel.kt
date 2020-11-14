package com.jay.randomuser.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.randomuser.data.api.RemoteApi
import com.jay.randomuser.view.base.BaseViewModel
import com.jay.randomuser.view.base.ViewModelType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.io.PipedReader

interface MainViewModelType : ViewModelType<MainViewModelType.Input, MainViewModelType.Output> {
    interface Input {
        fun onGenderFilter()
        fun onScrollTop()
    }

    interface Output {
        val gender: LiveData<String>
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

    private val _gender: MutableLiveData<String> = MutableLiveData()
    override val gender: LiveData<String>
        get() = _gender

    init {
        api.getUsers(1, 1, "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "$it: ")
            }, {
                Log.d(TAG, "$it: ")
            }).addTo(compositeDisposable)
    }

    override fun onGenderFilter() {
        //
    }

    override fun onScrollTop() {
        //
    }
}