package com.example.milsaboresapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.milsaboresapp.data.model.Categoria
import com.example.milsaboresapp.data.repository.CategoriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoriaViewModel(
    private val repository: CategoriaRepository = CategoriaRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoriaUiState())
    val uiState: StateFlow<CategoriaUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = CategoriaUiState(categorias = repository.getHabilitadas())
    }

    data class CategoriaUiState(
        val categorias: List<Categoria> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
