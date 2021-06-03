package com.sicpa.nytimedemo.di

import com.sicpa.nytimedemo.BuildConfig
import com.sicpa.nytimedemo.data.repository.ArticlesRemote
import com.sicpa.nytimedemo.remote.api.ApiService
import com.sicpa.nytimedemo.remote.api.ArticleService
import com.sicpa.nytimedemo.remote.repository.ArticlesRemoteImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideArticlesRemote(articlesRemoteImpl: ArticlesRemoteImpl): ArticlesRemote =
        articlesRemoteImpl

    @Provides
    @Singleton
    fun provideApiService(): ArticleService = ApiService.create(
        BuildConfig.DEBUG,
        BuildConfig.BASE_URL,
        BuildConfig.API_KEY
    )
}
