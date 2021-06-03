package com.sicpa.nytimedemo.data

import com.sicpa.nytimedemo.data.models.ArticleEntity
import com.sicpa.nytimedemo.data.repository.ArticlesDataSource
import com.sicpa.nytimedemo.data.repository.ArticlesRemote
import javax.inject.Inject

class ArticlesRemoteDataSource @Inject constructor(
    private val articlesRemote: ArticlesRemote
) : ArticlesDataSource {

    override suspend fun getArticles(
        articleType: ArticleType,
        query: String?
    ): List<ArticleEntity> {
        return articlesRemote.getArticles(articleType, query)
    }
}
