package com.sicpa.nytimedemo.remote.repository

import com.sicpa.nytimedemo.data.ArticleType
import com.sicpa.nytimedemo.data.models.ArticleEntity
import com.sicpa.nytimedemo.data.repository.ArticlesRemote
import com.sicpa.nytimedemo.remote.api.ArticleService
import com.sicpa.nytimedemo.remote.mapper.ArticleEntityMapper
import com.sicpa.nytimedemo.remote.mapper.SearchedEntityMapper
import javax.inject.Inject

class ArticlesRemoteImpl @Inject constructor(
    private val articleService: ArticleService,
    private val articleEntityMapper: ArticleEntityMapper,
    private val searchedEntityMapper: SearchedEntityMapper,
) : ArticlesRemote {

    override suspend fun getArticles(
        articleType: ArticleType,
        query: String?
    ): List<ArticleEntity> {
        return when (articleType) {
            ArticleType.MOST_EMAILED -> articleService.getMostEmailedArticles().results.map { articleModel ->
                articleEntityMapper.mapFromModel(articleModel)
            }
            ArticleType.MOST_SHARED -> articleService.getMostSharedArticles().results.map { articleModel ->
                articleEntityMapper.mapFromModel(articleModel)
            }
            ArticleType.MOST_VIEWED -> articleService.getMostViewedArticles().results.map { articleModel ->
                articleEntityMapper.mapFromModel(articleModel)
            }
            ArticleType.SEARCHED -> articleService.getSearchedArticles(
                query ?: ""
            ).response.docs.map { searchedModel ->
                searchedEntityMapper.mapFromModel(searchedModel)
            }
        }
    }
}
