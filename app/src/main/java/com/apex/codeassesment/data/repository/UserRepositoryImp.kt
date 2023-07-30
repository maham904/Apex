package com.apex.codeassesment.data.repository

import com.apex.codeassesment.data.model.Response.Results
import com.apex.codeassesment.data.model.Response.UserResponse
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.data.remote.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val userRepository: UserRepository) :
    UserRepositoryInterface {
    override suspend fun getRandomUser(): User {
        return userRepository.getRandomUser()!!
    }

    override suspend fun getUserList(): List<User> {
        return userRepository.getUSerByCount()
    }

    override suspend fun getSavedUser(): User {
        return userRepository.getSavedUser()
    }

    override suspend fun getSavedUsers(): List<User> {
        return userRepository.getUsers()
    }
}