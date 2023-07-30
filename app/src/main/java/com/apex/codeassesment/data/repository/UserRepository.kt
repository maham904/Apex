package com.apex.codeassesment.data.repository

import com.apex.codeassesment.data.local.LocalDatasource
import com.apex.codeassesment.data.model.Response.Info
import com.apex.codeassesment.data.model.Response.Results
import com.apex.codeassesment.data.model.Response.UserResponse
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.data.remote.RemoteDataSource
import retrofit2.Response
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

// TODO (2 points) : Add tests
// TODO (3 points) : Hide this class through an interface, inject the interface in the clients instead and remove warnings
class UserRepository @Inject constructor(
    private val localDataSource: LocalDatasource,
    private val remoteDataSource: RemoteDataSource
) {

    private val savedUser = AtomicReference(User())

    fun getSavedUser() = localDataSource.loadUser()

    fun getUser(forceUpdate: Boolean): User {
        if (forceUpdate) {
            val user = remoteDataSource.LoadUser()
            localDataSource.saveUser(user)
            savedUser.set(user)
        }
        return savedUser.get()
    }

    fun getUsers() = remoteDataSource.loadUsers()

    suspend fun getRandomUser(): User? {
        return remoteDataSource.getRandomUser().body()?.results?.get(0)
    }

   suspend fun getUSerByCount(): List<User> {
        return remoteDataSource.getUserByResults().body()?.results ?: emptyList()
    }
}
