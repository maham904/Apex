package com.apex.codeassesment.di

import com.apex.codeassesment.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class])
interface ViewModelComponent {

    fun inject(activity: MainActivity)
}