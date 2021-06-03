package com.sicpa.nytimedemo.data.repository

import com.sicpa.nytimedemo.data.ArticleType
import com.sicpa.nytimedemo.data.models.ArticleEntity

interface ArticlesDataSource {
    suspend fun getArticles(
        articleType: ArticleType,
        query: String?
    ): List<ArticleEntity>
}
