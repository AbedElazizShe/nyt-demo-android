package com.sicpa.nytimedemo.remote.models

data class SearchArticleModel(
    val headline: Headline,
    val pub_date: String,
)

data class Headline(
    val main: String
)
