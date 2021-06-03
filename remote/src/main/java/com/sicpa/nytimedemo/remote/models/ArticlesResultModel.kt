package com.sicpa.nytimedemo.remote.models

data class ArticlesResultModel(
    val status: String,
    val results: List<ArticleModel>
)
