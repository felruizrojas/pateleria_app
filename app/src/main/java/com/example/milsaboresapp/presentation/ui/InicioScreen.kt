package com.example.milsaboresapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.milsaboresapp.R

@Composable
fun InicioScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly // Distribuye el espacio equitativamente
    ) {
        SectionCard(
            navController = navController,
            imageRes = R.drawable.carrusel_nosotros,
            buttonText = "Conócenos",
            route = "about"
        )
        SectionCard(
            navController = navController,
            imageRes = R.drawable.carrusel_carta,
            buttonText = "Ver Carta",
            route = "catalogo"
        )
        SectionCard(
            navController = navController,
            imageRes = R.drawable.carrusel_blog,
            buttonText = "Leer Blog",
            route = null // Sin ruta por ahora
        )
    }
}

@Composable
fun SectionCard(
    navController: NavController,
    imageRes: Int,
    buttonText: String,
    route: String?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp) // Altura fija para cada tarjeta
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = buttonText, // <- ESTA ES LA CORRECCIÓN
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Escala la imagen para llenar el espacio
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        if (route != null) {
                            navController.navigate(route)
                        }
                        // Si la ruta es nula, el botón no hace nada
                    },
                    modifier = Modifier.padding(top = 8.dp),
                    enabled = route != null // El botón se deshabilita si no hay ruta
                ) {
                    Text(buttonText)
                }
            }
        }
    }
}
