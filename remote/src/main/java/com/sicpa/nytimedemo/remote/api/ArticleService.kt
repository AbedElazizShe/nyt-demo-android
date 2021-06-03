package com.sicpa.nytimedemo.remote.api

import com.sicpa.nytimedemo.remote.models.ArticlesResultModel
import com.sicpa.nytimedemo.remote.models.SearchArticlesResultModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("mostpopular/v2/emailed/7.json")
    suspend fun getMostEmailedArticles(): ArticlesResultModel

    @GET("mostpopular/v2/viewed/7.json")
    suspend fun getMostViewedArticles(): ArticlesResultModel

    @GET("mostpopular/v2/shared/7.json")
    suspend fun getMostSharedArticles(): ArticlesResultModel

    @GET("search/v2/articlesearch.json")
    suspend fun getSearchedArticles(@Query("q") query: String): SearchArticlesResultModel
}
