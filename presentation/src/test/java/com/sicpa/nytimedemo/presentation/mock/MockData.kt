package com.sicpa.nytimedemo.presentation.mock

import com.sicpa.nytimedemo.domain.models.Article

object MockData {

    fun getArticles(size: Int): List<Article> {
        val articles = mutableListOf<Article>()
        repeat(size) {
            articles.add(createArticle())
        }
        return articles
    }

    private fun createArticle(): Article {
        return Article(
            title = "test",
            publishedDate = "test"
        )
    }
}