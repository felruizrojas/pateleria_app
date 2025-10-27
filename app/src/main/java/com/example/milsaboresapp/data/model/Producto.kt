package com.example.milsaboresapp.data.model

import java.text.NumberFormat
import java.util.Locale

data class Producto(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int,
    val categoriaId: Int,
    val imageKey: String? = null
) {
    val enStock: Boolean get() = stock > 0

    val precioCLP: String
        get() {
            val nf = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
            // En CLP normalmente no se usan decimales
            nf.maximumFractionDigits = 0
            return nf.format(precio)
        }
}
