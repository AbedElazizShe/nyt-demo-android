package com.sicpa.nytimedemo.domain.mock

import com.sicpa.nytimedemo.domain.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MockArticles {

    fun getArticles(): Flow<List<Article>> = flow {
        val articles = listOf(
            Article(
                title = "test1",
                publishedDate = "test1"
            ),
            Article(
                title = "test2",
                publishedDate = "test2"
            )
        )

        emit(articles)
    }


}