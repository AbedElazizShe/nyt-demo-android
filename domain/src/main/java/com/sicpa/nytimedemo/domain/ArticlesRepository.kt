package com.sicpa.nytimedemo.domain

import com.sicpa.nytimedemo.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    suspend fun getArticles(params: Any, query: String?): Flow<List<Article>>
}
