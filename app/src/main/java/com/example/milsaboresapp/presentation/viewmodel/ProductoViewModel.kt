package com.example.milsaboresapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.milsaboresapp.data.model.Producto
import com.example.milsaboresapp.data.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductoViewModel(
    private val repository: ProductoRepository = ProductoRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductoUiState())
    val uiState: StateFlow<ProductoUiState> = _uiState.asStateFlow()

    // Guardamos todos los productos para no pedirlos siempre al repo
    private val allProducts: List<Producto>

    init {
        allProducts = repository.getAll()
        // Estado inicial: mostrar todos los productos disponibles
        _uiState.value = ProductoUiState(
            productos = allProducts.filter { it.enStock }
        )
    }

    /**
     * Filtra los productos por el ID de categoría.
     * Si el ID es null, muestra todos los productos disponibles.
     */
    fun filterProductosByCategoria(categoriaId: Int?) {
        val productosFiltrados = if(categoriaId == null) {
            allProducts.filter { it.enStock }
        } else {
            allProducts.filter { it.categoriaId == categoriaId && it.enStock }
        }
        _uiState.update { it.copy(productos = productosFiltrados) }
    }

    data class ProductoUiState(
        val productos: List<Producto> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )
}
