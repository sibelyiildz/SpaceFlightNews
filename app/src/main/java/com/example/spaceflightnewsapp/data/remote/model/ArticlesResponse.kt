package com.example.spaceflightnewsapp.data.remote.model

data class ArticlesResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<NewsModel>?
)

data class NewsModel(
    val events: List<Event>?,
    val featured: Boolean?,
    val id: Int,
    val image_url: String?,
    val launches: List<Launche>?,
    val news_site: String?,
    val published_at: String?,
    val summary: String?,
    val title: String?,
    val updated_at: String?,
    val url: String?
)

data class Event(
    val event_id: Int?,
    val provider: String?
)

data class Launche(
    val launch_id: String?,
    val provider: String?
)