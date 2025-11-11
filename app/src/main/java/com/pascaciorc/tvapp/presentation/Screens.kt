package com.pascaciorc.tvapp.presentation

import kotlinx.serialization.Serializable

@Serializable
object Dashboard

@Serializable
data class MediaItemDetails(val mediaItem: String)

@Serializable
data class Player(val videoUrl: String)