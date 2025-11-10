package com.pascaciorc.tvapp.presentation.dashboard

data class DashboardState(
    val loading: Boolean = false,
    val data: List<Category> = emptyList(),
)
