package com.apex.codeassesment.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apex.codeassesment.data.model.Response.Info
import com.apex.codeassesment.data.model.Response.Results
import com.apex.codeassesment.data.model.Response.UserResponse
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _singleUserLiveData: MutableLiveData<User> = MutableLiveData()
    val singleUserLiveData: LiveData<User> get() = _singleUserLiveData

    private val _userListLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val userListLiveData: LiveData<List<User>> get() = _userListLiveData


    init {
        viewModelScope.launch(Dispatchers.Main) {
            _singleUserLiveData.value = userRepository.getRandomUser()
        }
    }
        fun fetchUsers() {
            viewModelScope.launch {
                _userListLiveData.value = userRepository.getUSerByCount()
            }

    }


}