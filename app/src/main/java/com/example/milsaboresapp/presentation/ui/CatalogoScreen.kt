package com.example.milsaboresapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.milsaboresapp.data.model.Categoria
import com.example.milsaboresapp.data.model.Producto
import com.example.milsaboresapp.presentation.viewmodel.CategoriaViewModel
import com.example.milsaboresapp.presentation.viewmodel.ProductoViewModel

@Composable
fun CatalogoScreen(
    categoriaViewModel: CategoriaViewModel,
    productoViewModel: ProductoViewModel
) {
    val categoriaUiState by categoriaViewModel.uiState.collectAsState()
    val productoUiState by productoViewModel.uiState.collectAsState()

    var selectedCategoria by remember { mutableStateOf<Categoria?>(null) }

    LaunchedEffect(selectedCategoria) {
        productoViewModel.filterProductosByCategoria(selectedCategoria?.id)
    }

    Column {
        CategoriasList(
            categorias = categoriaUiState.categorias,
            onCategoriaSelected = { categoria ->
                selectedCategoria = if (selectedCategoria == categoria) null else categoria
            }
        )
        ProductosList(productos = productoUiState.productos)
    }
}

@Composable
fun CategoriasList(
    categorias: List<Categoria>,
    onCategoriaSelected: (Categoria) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categorias) { categoria ->
            Button(onClick = { onCategoriaSelected(categoria) }) {
                Text(text = categoria.nombre)
            }
        }
    }
}

@Composable
fun ProductosList(productos: List<Producto>) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(productos) { producto ->
            Card(modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth()) {
                Row(modifier = Modifier.padding(8.dp)) {
                    val context = LocalContext.current
                    val resourceId = remember(producto.imageKey) {
                        if (!producto.imageKey.isNullOrBlank()) {
                            context.resources.getIdentifier(
                                producto.imageKey,
                                "drawable",
                                context.packageName
                            )
                        } else {
                            0
                        }
                    }

                    if (resourceId != 0) {
                        Image(
                            painter = painterResource(id = resourceId),
                            contentDescription = producto.nombre,
                            modifier = Modifier.size(64.dp).padding(end = 8.dp)
                        )
                    } else {
                        // Placeholder for the image
                        Box(modifier = Modifier.size(64.dp).padding(end = 8.dp))
                    }
                    Column {
                        Text(text = producto.nombre, style = MaterialTheme.typography.titleMedium)
                        Text(text = producto.descripcion)
                        Text(text = producto.precioCLP, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}
