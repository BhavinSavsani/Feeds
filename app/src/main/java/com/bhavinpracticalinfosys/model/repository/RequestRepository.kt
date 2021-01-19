package com.bhavinpracticalinfosys.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bhavinpracticalinfosys.api.ApiService
import com.bhavinpracticalinfosys.api.RequestState
import com.bhavinpracticalinfosys.model.Feed
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestRepository @Inject constructor(
    apiService: ApiService
) : BaseRepo(apiService) {

    fun getFeeds(feeds : MutableLiveData<RequestState<Feed>>) {
        return apiService.getFeed().callApi(feeds)
    }


}


