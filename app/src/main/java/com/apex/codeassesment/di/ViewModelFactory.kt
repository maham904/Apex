package com.apex.codeassesment.di

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton


class ViewModelFactory @Inject constructor(private val viewModelMap: Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>) :
    ViewModelProvider.Factory {

    @NonNull
    @SuppressWarnings("unchecked")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = viewModelMap[modelClass] ?: viewModelMap.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value

        if (creator != null) {
            try {
                @Suppress("UNCHECKED_CAST")
                return creator as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}