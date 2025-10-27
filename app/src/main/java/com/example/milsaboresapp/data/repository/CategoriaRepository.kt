package com.example.milsaboresapp.data.repository

import com.example.milsaboresapp.data.model.Categoria

/**
 * Repositorio en memoria, sin dependencias de Android.
 * API mínima para que los ViewModels puedan trabajar.
 */
class CategoriaRepository(
    private val categoriasSeed: List<Categoria> = listOf(
        Categoria(id = 1, nombre = "Tortas Cuadradas", habilitado = true),
        Categoria(id = 2, nombre = "Tortas Circulares", habilitado = true),
        Categoria(id = 3, nombre = "Postres Individuales", habilitado = true),
        Categoria(id = 4, nombre = "Productos sin Azúcar", habilitado = true),
        Categoria(id = 5, nombre = "Pastelería Tradicional", habilitado = true),
        Categoria(id = 6, nombre = "Productos sin Gluten", habilitado = true),
        Categoria(id = 7, nombre = "Productos Veganos", habilitado = true),
        Categoria(id = 8, nombre = "Tortas Especiales", habilitado = true)
    )
) {

    // Estado interno simple; si quieres inmutabilidad estricta, expón copias.
    private val categorias: MutableList<Categoria> = categoriasSeed.toMutableList()

    fun getAll(): List<Categoria> = categorias.toList()

    fun getHabilitadas(): List<Categoria> = categorias.filter { it.habilitado }

    fun findById(id: Int): Categoria? = categorias.firstOrNull { it.id == id }

    fun upsert(categoria: Categoria) {
        val idx = categorias.indexOfFirst { it.id == categoria.id }
        if (idx >= 0) categorias[idx] = categoria else categorias.add(categoria)
    }

    fun setHabilitada(id: Int, habilitada: Boolean): Boolean {
        val idx = categorias.indexOfFirst { it.id == id }
        if (idx < 0) return false
        val c = categorias[idx]
        categorias[idx] = c.copy(habilitado = habilitada)
        return true
    }
}
