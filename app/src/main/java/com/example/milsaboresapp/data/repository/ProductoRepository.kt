package com.example.milsaboresapp.data.repository

import com.example.milsaboresapp.data.model.Producto

/**
 * Repositorio en memoria sin uso de R.drawable (acoplado a Android).
 * La UI puede mapear Product.imageKey -> drawable o URL cuando corresponda.
 */
class ProductoRepository(
    private val productosSeed: List<Producto> = listOf(
        //Tortas cuadradas
        Producto(
            id = "TC001",
            nombre = "Torta Cuadrada de Chocolate",
            descripcion = "Deliciosa torta de chocolate con capas de ganache y un toque de avellanas. Personalizable con mensajes especiales.",
            precio = 45000.0,
            stock = 10,
            categoriaId = 1,
            imageKey = "torta_cuadrada_de_chocolate"
        ),
        Producto(
            id = "TC002",
            nombre = "Torta Cuadrada de frutas",
            descripcion = "Una mezcla de frutas frescas y crema chantilly sobre un suave bizcocho de vainilla, ideal para celebraciones.",
            precio = 50000.0,
            stock = 10,
            categoriaId = 1,
            imageKey = "torta_cuadrada_de_frutas"
        ),
        //Tortas circulares
        Producto(
            id = "TT001",
            nombre = "Torta Circular de Vainilla",
            descripcion = "Bizcocho de vainilla clásico relleno con crema pastelera y cubierto con un glaseado dulce, perfecto para cualquier ocasión.",
            precio = 40000.0,
            stock = 10,
            categoriaId = 2,
            imageKey = "torta_circular_de_vainilla"
        ),
        Producto(
            id = "TT002",
            nombre = "Torta Circular de Manjar",
            descripcion = "Torta tradicional chilena con manjar y nueces, un deleite para los amantes de los sabores dulces y clásicos.",
            precio = 42000.0,
            stock = 10,
            categoriaId = 2,
            imageKey = "torta_circular_de_manjar"
        ),
        //
        Producto(
            id = "PI001",
            nombre = "Mousse de Chocolate",
            descripcion = "Postre individual cremoso y suave, hecho con chocolate de alta calidad, ideal para los amantes del chocolate.",
            precio = 5000.0,
            stock = 10,
            categoriaId = 3,
            imageKey = "mousse_de_chocolate"
        ),
        Producto(
            id = "PI002",
            nombre = "Tiramisú Clásico",
            descripcion = "Un postre italiano individual con capas de café, mascarpone y cacao, perfecto para finalizar cualquier comida.",
            precio = 5500.0,
            stock = 10,
            categoriaId = 3,
            imageKey = "tiramisu_clasico"
        ),
        //
        Producto(
            id = "PSA001",
            nombre = "Torta Sin Azúcar de Naranja",
            descripcion = "Torta ligera y deliciosa, endulzada naturalmente, ideal para quienes buscan opciones más saludables.",
            precio = 48000.0,
            stock = 10,
            categoriaId = 4,
            imageKey = "torta_sin_azucar_de_naranja"
        ),
        Producto(
            id = "PSA002",
            nombre = "Cheesecake Sin Azúcar",
            descripcion = "Suave y cremoso, este cheesecake es una opción perfecta para disfrutar sin culpa.",
            precio = 47000.0,
            stock = 10,
            categoriaId = 4,
            imageKey = "cheesecake_sin_azucar"
        ),
        //
        Producto(
            id = "PT001",
            nombre = "Empanada de Manzana",
            descripcion = "Pastelería tradicional rellena de manzanas especiadas, perfecta para un dulce desayuno o merienda.",
            precio = 3000.0,
            stock = 10,
            categoriaId = 5,
            imageKey = "empanada_de_manzana"
        ),
        Producto(
            id = "PT002",
            nombre = "Tarta de Santiago",
            descripcion = "Tradicional tarta española hecha con almendras, azúcar, y huevos, una delicia para los amantes de los postres clásicos.",
            precio = 6000.0,
            stock = 10,
            categoriaId = 5,
            imageKey = "tarta_de_santiago"
        ),
        //
        Producto(
            id = "PG001",
            nombre = "Brownie Sin Gluten",
            descripcion = "Rico y denso, este brownie es perfecto para quienes necesitan evitar el gluten sin sacrificar el sabor.",
            precio = 4000.0,
            stock = 10,
            categoriaId = 6,
            imageKey = "brownie_sin_gluten"
        ),
        Producto(
            id = "PG002",
            nombre = "Pan Sin Gluten",
            descripcion = "Suave y esponjoso, ideal para sándwiches o para acompañar cualquier comida.",
            precio = 3500.0,
            stock = 10,
            categoriaId = 6,
            imageKey = "pan_sin_gluten"
        ),
        //
        Producto(
            id = "PV001",
            nombre = "Torta Vegana de Chocolate",
            descripcion = "Torta de chocolate húmeda y deliciosa, hecha sin productos de origen animal, perfecta para veganos.",
            precio = 50000.0,
            stock = 10,
            categoriaId = 7,
            imageKey = "torta_vegana_de_chocolate"
        ),
        Producto(
            id = "PV002",
            nombre = "Galletas Veganas de Avena",
            descripcion = "Crujientes y sabrosas, estas galletas son una excelente opción para un snack saludable y vegano.",
            precio = 4500.0,
            stock = 10,
            categoriaId = 7,
            imageKey = "galletas_veganas_de_avena"
        ),
        //
        Producto(
            id = "TE001",
            nombre = "Torta Especial de Cumpleaños",
            descripcion = "Diseñada especialmente para celebraciones, personalizable con decoraciones y mensajes únicos.",
            precio = 55000.0,
            stock = 10,
            categoriaId = 8,
            imageKey = "torta_especial_de_cumpleanos"
        ),
        Producto(
            id = "TE002",
            nombre = "Torta Especial de Boda",
            descripcion = "Elegante y deliciosa, esta torta está diseñada para ser el centro de atención en cualquier boda.",
            precio = 60000.0,
            stock = 10,
            categoriaId = 8,
            imageKey = "torta_especial_de_boda"
        )
    )
) {

    private val productos: MutableList<Producto> = productosSeed.toMutableList()

    fun getAll(): List<Producto> = productos.toList()

    fun getByCategoria(categoriaId: Int): List<Producto> =
        productos.filter { it.categoriaId == categoriaId }

    fun getDisponibles(): List<Producto> = productos.filter { it.enStock }

    fun findById(id: String): Producto? = productos.firstOrNull { it.id == id }

    fun search(query: String): List<Producto> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return emptyList()
        return productos.filter { p ->
            p.nombre.lowercase().contains(q) ||
                    p.descripcion.lowercase().contains(q)
        }
    }

    fun upsert(producto: Producto) {
        val idx = productos.indexOfFirst { it.id == producto.id }
        if (idx >= 0) productos[idx] = producto else productos.add(producto)
    }

    fun updateStock(id: String, newStock: Int): Boolean {
        val idx = productos.indexOfFirst { it.id == id }
        if (idx < 0) return false
        val p = productos[idx]
        productos[idx] = p.copy(stock = newStock.coerceAtLeast(0))
        return true
    }
}
