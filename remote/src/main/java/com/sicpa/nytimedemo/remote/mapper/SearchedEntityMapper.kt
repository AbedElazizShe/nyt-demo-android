package com.sicpa.nytimedemo.remote.mapper

import com.sicpa.nytimedemo.data.models.ArticleEntity
import com.sicpa.nytimedemo.remote.models.SearchArticleModel
import javax.inject.Inject

class SearchedEntityMapper @Inject constructor() :
    EntityMapper<SearchArticleModel, ArticleEntity> {
    override fun mapFromModel(model: SearchArticleModel): ArticleEntity {
        return ArticleEntity(
            title = model.headline.main,
            publishedDate = model.pub_date
        )
    }
}
