package com.sicpa.nytimedemo.data.mock

import com.sicpa.nytimedemo.data.models.ArticleEntity

object MockArticles {

    fun getArticles(): List<ArticleEntity> = listOf(
        ArticleEntity(
            title = "test1",
            publishedDate = "test1"
        ),
        ArticleEntity(
            title = "test2",
            publishedDate = "test2"
        )
    )

    fun getArticle(): ArticleEntity =
        ArticleEntity(
            title = "test1",
            publishedDate = "test1"
        )


}