package com.example.milsaboresapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.milsaboresapp.R

@Composable
fun AboutScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text("Quienes somos", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "En Pastelería Mil Sabores celebramos 50 años de historia endulzando momentos únicos y siendo un referente de la repostería chilena. Desde nuestro récord Guinness en 1995, cuando colaboramos en la creación de la torta más grande del mundo, mantenemos viva la tradición de innovar y sorprender con cada creación. Hoy renovamos nuestro sistema de ventas online para que nuestros clientes disfruten de una experiencia moderna y accesible, llevando la dulzura directamente a sus hogares.",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.vista_pasteleria_mil_sabores),
                contentDescription = "Vista de la pastelería",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text("Misión", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "una experiencia dulce y memorable, proporcionando tortas y productos de repostería de alta calidad para todas las ocasiones. Celebramos nuestras raíces históricas y fomentamos la creatividad en la repostería chilena.",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.diversos_productos),
                contentDescription = "Diversos productos de repostería",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text("Visión", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Convertirnos en la tienda online líder de repostería en Chile, reconocida por la calidad, la innovación y el impacto positivo en la comunidad. Queremos ser una plataforma de impulso para las nuevas generaciones de talentos gastronómicos.",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.persona_trabajando_en_una_cocina),
                contentDescription = "Persona trabajando en la cocina",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text("Impacto comunitario", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Cada compra apoya a estudiantes de gastronomía y a la comunidad local, contribuyendo a que nuevas generaciones de reposteros sigan creando y compartiendo su arte.",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.estudiante_de_reposteria_aprendiendo_en_la_cocina),
                contentDescription = "Estudiante aprendiendo repostería",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}
