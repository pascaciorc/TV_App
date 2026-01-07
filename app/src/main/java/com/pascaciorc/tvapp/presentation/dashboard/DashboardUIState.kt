package com.pascaciorc.tvapp.presentation.dashboard

data class DashboardUIState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList()
)
