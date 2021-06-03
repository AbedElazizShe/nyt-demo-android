package com.sicpa.nytimedemo.remote.models

data class SearchArticlesResultModel(
    val status: String,
    val response: Docs
)

data class Docs(
    val docs: List<SearchArticleModel>
)
