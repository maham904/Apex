package com.apex.codeassesment.data.repository

import com.apex.codeassesment.data.model.Response.Results
import com.apex.codeassesment.data.model.Response.UserResponse
import com.apex.codeassesment.data.model.User

interface UserRepositoryInterface  {
    suspend fun getRandomUser(): User
    suspend fun getUserList(): List<User>
    suspend fun getSavedUser(): User
    suspend fun getSavedUsers(): List<User>

}