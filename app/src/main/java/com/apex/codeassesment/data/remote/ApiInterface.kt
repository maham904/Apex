package com.apex.codeassesment.data.remote

import com.apex.codeassesment.data.model.Response.UserResponse
import com.apex.codeassesment.data.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/api")
   suspend  fun getRandomUSer(): Response<UserResponse>


    @GET("/api?results=10")
    suspend fun geUserById(): Response<UserResponse>
}