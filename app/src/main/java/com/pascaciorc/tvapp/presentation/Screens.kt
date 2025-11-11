package com.pascaciorc.tvapp.presentation

import kotlinx.serialization.Serializable

@Serializable
object Dashboard

@Serializable
data class MediaItemDetails(val mediaItem: String)