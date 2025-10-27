package com.example.milsaboresapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.milsaboresapp.data.repository.CategoriaRepository
import com.example.milsaboresapp.data.repository.ProductoRepository
import com.example.milsaboresapp.presentation.ui.AboutScreen
import com.example.milsaboresapp.presentation.ui.CatalogoScreen
import com.example.milsaboresapp.presentation.ui.InicioScreen
import com.example.milsaboresapp.presentation.viewmodel.CategoriaViewModel
import com.example.milsaboresapp.presentation.viewmodel.ProductoViewModel
import com.example.milsaboresapp.ui.theme.MilSaboresAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MilSaboresAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

// Generic ViewModel Factory
class ViewModelFactory(
    private val categoriaRepository: CategoriaRepository,
    private val productoRepository: ProductoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoriaViewModel(categoriaRepository) as T
        }
        if (modelClass.isAssignableFrom(ProductoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductoViewModel(productoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val factory = remember {
        ViewModelFactory(CategoriaRepository(), ProductoRepository())
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text(text = "Inicio") },
                    selected = currentRoute == "inicio",
                    onClick = {
                        navController.navigate("inicio") { launchSingleTop = true }
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Conócenos") },
                    selected = currentRoute == "about",
                    onClick = {
                        navController.navigate("about") { launchSingleTop = true }
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Carta") },
                    selected = currentRoute == "catalogo",
                    onClick = {
                        navController.navigate("catalogo") { launchSingleTop = true }
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        // El logo se muestra en el centro si no estamos en la pantalla de inicio
                        if (currentRoute != "inicio") {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.logo_tienda),
                                    contentDescription = "Logo de la tienda"
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        // Botón de hamburguesa a la izquierda
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply { if (isClosed) open() else close() }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    },
                    actions = {
                        // Logo a la derecha, solo en la pantalla de inicio
                        if (currentRoute == "inicio") {
                            Image(
                                painter = painterResource(id = R.drawable.logo_tienda),
                                contentDescription = "Logo de la tienda"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    )
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(navController = navController, startDestination = "inicio") {
                    composable("inicio") { InicioScreen(navController) }
                    composable("catalogo") {
                        val categoriaViewModel: CategoriaViewModel = viewModel(factory = factory)
                        val productoViewModel: ProductoViewModel = viewModel(factory = factory)
                        CatalogoScreen(categoriaViewModel, productoViewModel)
                    }
                    composable("about") { AboutScreen() }
                }
            }
        }
    }
}