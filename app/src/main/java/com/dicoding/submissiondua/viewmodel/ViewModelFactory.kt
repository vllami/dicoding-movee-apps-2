package com.dicoding.submissiondua.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.submissiondua.data.remote.repository.Repository
import com.dicoding.submissiondua.ui.details.DetailsViewModel
import com.dicoding.submissiondua.ui.home.HomeViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory as ViewModelInstanceFactory
import com.dicoding.submissiondua.di.Injection.provideRepository as InjectionProvideRepository

class ViewModelFactory private constructor(private val repository: Repository) : ViewModelInstanceFactory() {

    companion object {
        @Volatile
        private var viewModelFactory: ViewModelFactory? = null

        fun getViewModelFactory(): ViewModelFactory {
            return viewModelFactory ?: synchronized(this) {
                viewModelFactory ?: ViewModelFactory(InjectionProvideRepository()).apply {
                    viewModelFactory = this
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        modelClass.apply {
            return when {
                isAssignableFrom(HomeViewModel::class.java) -> {
                    HomeViewModel(repository) as T
                }
                isAssignableFrom(DetailsViewModel::class.java) -> {
                    DetailsViewModel(repository) as T
                }
                else -> throw Throwable("Unknown ViewModel $name")
            }
        }
    }

}