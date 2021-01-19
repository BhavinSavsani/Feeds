package com.bhavinpracticalinfosys.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bhavinpracticalinfosys.api.RequestState
import com.bhavinpracticalinfosys.model.Feed
import com.bhavinpracticalinfosys.model.repository.RequestRepository
import com.bhavinpracticalinfosys.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val requestRepo: RequestRepository
) : BaseViewModel() {

    var feeds: MutableLiveData<RequestState<Feed>> = MutableLiveData()

    fun getFeeds() = requestRepo.getFeeds(feeds)


    fun retry() {
        requestRepo.retry()
    }


}