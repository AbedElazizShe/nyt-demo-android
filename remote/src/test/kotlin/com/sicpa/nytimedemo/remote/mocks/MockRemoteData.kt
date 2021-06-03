package com.sicpa.nytimedemo.remote.mocks

import com.sicpa.nytimedemo.data.models.ArticleEntity
import com.sicpa.nytimedemo.remote.models.*
import java.util.*

object MockRemoteData {

    fun getResponse(size: Int): ArticlesResultModel {
        return ArticlesResultModel(status = "ok", getFakeArticlesModel(size))
    }

    fun getSearchResponse(size: Int): SearchArticlesResultModel {
        return SearchArticlesResultModel(status = "ok", Docs(getFakeSearchArticlesModel(size)))
    }

    private fun getFakeArticlesModel(size: Int): List<ArticleModel> {
        val articles = mutableListOf<ArticleModel>()
        repeat(size) {
            articles.add(getArticleModel())
        }
        return articles
    }

    private fun getFakeSearchArticlesModel(size: Int): List<SearchArticleModel> {
        val articles = mutableListOf<SearchArticleModel>()
        repeat(size) {
            articles.add(getSearchArticleModel())
        }
        return articles
    }

    fun getArticleModel(): ArticleModel {
        return ArticleModel(
            title = UUID.randomUUID().toString(),
            published_date = UUID.randomUUID().toString()
        )
    }

    fun getSearchArticleModel(): SearchArticleModel {
        return SearchArticleModel(
            headline = Headline( UUID.randomUUID().toString()),
            pub_date = UUID.randomUUID().toString()
        )
    }

}