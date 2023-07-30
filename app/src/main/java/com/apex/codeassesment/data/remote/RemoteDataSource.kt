package com.apex.codeassesment.data.remote

import com.apex.codeassesment.data.model.Response.Info
import com.apex.codeassesment.data.model.Response.UserResponse
import com.apex.codeassesment.data.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

// TODO (2 points): Add tests
class RemoteDataSource @Inject constructor(private val retrofit: ApiInterface) {

  // TODO (5 points): Load data from endpoint https://randomuser.me/api
  fun LoadUser() = User.createRandom()

   suspend fun getRandomUser() : Response<UserResponse> {
    return retrofit.getRandomUSer()
  }
  // TODO (3 points): Load data from endpoint https://randomuser.me/api?results=10
  suspend fun getUserByResults(): Response<UserResponse> {
    return retrofit.geUserById()
  }
  // TODO (Optional Bonus: 3 points): Handle succes and failure from endpoints
  fun loadUsers() = (0..10).map { User.createRandom() }
}
