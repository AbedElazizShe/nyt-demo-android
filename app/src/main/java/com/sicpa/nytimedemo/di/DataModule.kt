package com.sicpa.nytimedemo.di

import com.sicpa.nytimedemo.data.ArticlesRepositoryImpl
import com.sicpa.nytimedemo.domain.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideArticlesRepository(articlesRepositoryImpl: ArticlesRepositoryImpl): ArticlesRepository =
        articlesRepositoryImpl
}
