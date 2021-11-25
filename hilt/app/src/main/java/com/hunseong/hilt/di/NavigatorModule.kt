package com.hunseong.hilt.di

import com.hunseong.hilt.navigator.Navigator
import com.hunseong.hilt.navigator.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigatorModule {

    @Binds
    abstract fun bindNavigator(impl: NavigatorImpl) : Navigator
}