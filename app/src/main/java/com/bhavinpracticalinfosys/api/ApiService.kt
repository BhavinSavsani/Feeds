package com.bhavinpracticalinfosys.api

import com.bhavinpracticalinfosys.model.Feed
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getFeed(): Flowable<Feed>
}