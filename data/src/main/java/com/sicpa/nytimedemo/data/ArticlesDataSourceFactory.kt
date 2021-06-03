package com.sicpa.nytimedemo.data

import com.sicpa.nytimedemo.data.repository.ArticlesDataSource
import javax.inject.Inject

open class ArticlesDataSourceFactory @Inject constructor(
    private val remoteDataSource: ArticlesRemoteDataSource
) {
    open suspend fun getDataSource(): ArticlesDataSource = remoteDataSource
}
