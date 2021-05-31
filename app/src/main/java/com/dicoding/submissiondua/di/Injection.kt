package com.dicoding.submissiondua.di

import com.dicoding.submissiondua.data.remote.repository.Repository
import com.dicoding.submissiondua.data.remote.repository.Repository.Companion.getRepository
import com.dicoding.submissiondua.data.remote.source.RemoteDataSource.Companion.getRemoteDataSource

object Injection {

    fun provideRepository(): Repository {
        getRemoteDataSource().also {
            return getRepository(it)
        }
    }

}