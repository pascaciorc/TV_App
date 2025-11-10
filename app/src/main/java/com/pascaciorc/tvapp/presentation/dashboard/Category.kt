package com.pascaciorc.tvapp.presentation.dashboard

data class Category(
    val id: String,
    val title: String,
    val items: List<MediaItem>
)
