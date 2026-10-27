package com.duoc.notar_ia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duoc.notar_ia.repository.UsuarioRepository
import com.duoc.notar_ia.ui.screens.HomeScreen
import com.duoc.notar_ia.ui.screens.LoginScreen
import com.duoc.notar_ia.ui.screens.RegistroScreen
import com.duoc.notar_ia.viewmodel.LoginViewModelFactory
import com.duoc.notar_ia.viewmodel.RegistroViewModelFactory

object Routes {
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val HOME = "home"
}

@Composable
fun AppNavigation(usuarioRepository: UsuarioRepository) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                viewModel = viewModel(factory = LoginViewModelFactory(usuarioRepository)),
                onNavigateToRegistro = { navController.navigate(Routes.REGISTRO) },
                onLoginExitoso = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.REGISTRO) {
            RegistroScreen(
                viewModel = viewModel(factory = RegistroViewModelFactory(usuarioRepository)),
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Routes.HOME) {
            HomeScreen(
                onTramiteClick = { /* TODO: navegar a detalle del trámite */ },
                onNavegar = { destino ->
                    if (destino == "Perfil") {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.HOME) { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}
