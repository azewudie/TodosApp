package com.test.todos.di

import com.test.todos.models.IRepository
import com.test.todos.models.Repository
import com.test.todos.models.remote.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideRepository(service:Service): IRepository = Repository(service)
}