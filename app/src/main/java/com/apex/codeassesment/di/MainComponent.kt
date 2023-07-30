package com.apex.codeassesment.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.apex.codeassesment.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MainModule::class,ViewModelModule::class])
interface MainComponent {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance applicationContext: Context): MainComponent
  }

  interface Injector {
    val mainComponent: MainComponent
  }
  fun inject(mainActivity: MainActivity)
}