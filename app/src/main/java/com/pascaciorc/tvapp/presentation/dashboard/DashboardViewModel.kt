package com.pascaciorc.tvapp.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascaciorc.tvapp.domain.GetDashboardDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val useCase: GetDashboardDataUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<DashboardUIState> = MutableStateFlow(DashboardUIState())
    val uiState = _uiState.asStateFlow()
    var dashboardData = emptyList<Category>()

    init {
        loadDashboard()
    }

    fun loadDashboard() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            dashboardData = useCase.invoke()
            _uiState.update { it.copy(isLoading = false, categories = dashboardData) }
        }
    }

    fun filterCategories(filterText: String) {
        val filteredCategories = dashboardData.filter { it.title.startsWith(filterText) }
        _uiState.update { it.copy(categories = filteredCategories) }
    }

    fun resetFilter() {
        _uiState.update { it.copy(categories = dashboardData) }
    }
}