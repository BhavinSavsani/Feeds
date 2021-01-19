package com.bhavinpracticalinfosys.model.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bhavinpracticalinfosys.api.ApiService
import com.bhavinpracticalinfosys.api.InternetException
import com.bhavinpracticalinfosys.api.RequestState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers


abstract class BaseRepo constructor(
    protected val apiService: ApiService
) {

    private var retryCompletable: Completable? = null

    @SuppressLint("CheckResult")
    protected fun <T> Flowable<T>.callApi(
        liveData: MutableLiveData<RequestState<T>>,
        onSuccess: ((T) -> Unit)? = null
    ) {
        liveData.postValue(RequestState.progress())
        this.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess?.invoke(it)
                liveData.postValue(RequestState.success(it))
            }, {
                setRetry(Action { callApi(liveData) })
                if (it is InternetException)
                    liveData.postValue(RequestState.internetError())
                else
                    liveData.postValue(RequestState.error(it.message))
            })
    }

    fun retry() {
        if (retryCompletable != null) {
            retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}