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

    private val _uiState: MutableStateFlow<DashboardState> = MutableStateFlow(DashboardState())
    val uiState = _uiState.asStateFlow()

    init {
        loadDashboard()
    }

    fun loadDashboard() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(loading = true) }
            val data = useCase.invoke()
            _uiState.update { it.copy(loading = false, data = data) }
        }
    }
}