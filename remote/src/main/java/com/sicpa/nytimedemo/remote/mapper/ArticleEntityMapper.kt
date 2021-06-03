package com.sicpa.nytimedemo.remote.mapper

import com.sicpa.nytimedemo.data.models.ArticleEntity
import com.sicpa.nytimedemo.remote.models.ArticleModel
import javax.inject.Inject

class ArticleEntityMapper @Inject constructor() :
    EntityMapper<ArticleModel, ArticleEntity> {
    override fun mapFromModel(model: ArticleModel): ArticleEntity {
        return ArticleEntity(
            title = model.title,
            publishedDate = model.published_date
        )
    }
}
