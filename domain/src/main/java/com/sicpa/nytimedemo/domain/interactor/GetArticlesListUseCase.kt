package com.sicpa.nytimedemo.domain.interactor

import com.sicpa.nytimedemo.domain.ArticlesRepository
import com.sicpa.nytimedemo.domain.models.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetArticlesListBaseUseCase = BaseUseCase<Any, Flow<List<Article>>>

class GetArticlesListUseCase @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : GetArticlesListBaseUseCase {
    override suspend fun invoke(params: Any): Flow<List<Article>> {

        val request = params as ArticleRequest
        return articlesRepository.getArticles(
            request.articleType,
            request.query
        )
    }
}
