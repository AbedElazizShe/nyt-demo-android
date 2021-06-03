package com.sicpa.nytimedemo.data.mapper

import com.sicpa.nytimedemo.data.models.ArticleEntity
import com.sicpa.nytimedemo.domain.models.Article
import javax.inject.Inject

class ArticleMapper @Inject constructor() : Mapper<ArticleEntity, Article> {
    override fun mapFromEntity(type: ArticleEntity): Article {
        return Article(
            title = type.title,
            publishedDate = type.publishedDate
        )
    }
}
