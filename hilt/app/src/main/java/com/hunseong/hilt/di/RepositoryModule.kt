package com.hunseong.hilt.di

import com.hunseong.hilt.data.LogDBRepository
import com.hunseong.hilt.data.LogInMemoryRepository
import com.hunseong.hilt.data.LogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class LogInMemory

@Qualifier
annotation class LogDB


@InstallIn(SingletonComponent::class)
@Module
abstract class LogDBModule {

    @LogDB
    @Binds
    @Singleton
    abstract fun bindLogDBRepository(impl: LogDBRepository) : LogRepository
}

@InstallIn(ActivityComponent::class)
@Module
abstract class LogInMemoryModule {

    @LogInMemory
    @Binds
    @ActivityScoped
    abstract fun bindLogInMemoryRepository(impl: LogInMemoryRepository) : LogRepository
}