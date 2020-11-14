package com.jay.randomuser.data.api

import com.jay.randomuser.data.response.ListResponse
import com.jay.randomuser.data.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    @GET("/api")
    fun getUsers(
        @Query("page")
        page: Int,
        @Query("results")
        results: Int,
        @Query("seed")
        seed: String,
        @Query("gender")
        gender: String? = null
    ): Single<ListResponse<UserResponse>>
}