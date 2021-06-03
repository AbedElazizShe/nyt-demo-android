package com.sicpa.nytimedemo.data

import com.sicpa.nytimedemo.data.mapper.ArticleMapper
import com.sicpa.nytimedemo.domain.ArticlesRepository
import com.sicpa.nytimedemo.domain.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val dataSourceFactory: ArticlesDataSourceFactory,
    private val articleMapper: ArticleMapper
) : ArticlesRepository {
    override suspend fun getArticles(
        params: Any,
        query: String?
    ): Flow<List<Article>> = flow {
        val articlesList =
            dataSourceFactory.getDataSource()
                .getArticles(params as ArticleType, query).map { article ->
                articleMapper.mapFromEntity(article)
            }
        emit(articlesList)
    }
}
