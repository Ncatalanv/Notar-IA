package com.duoc.notar_ia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duoc.notar_ia.repository.UsuarioRepository
import com.duoc.notar_ia.ui.screens.HomeScreen
import com.duoc.notar_ia.ui.screens.LoginScreen
import com.duoc.notar_ia.ui.screens.PerfilScreen
import com.duoc.notar_ia.ui.screens.RegistroScreen
import com.duoc.notar_ia.utils.SessionManager
import com.duoc.notar_ia.viewmodel.LoginViewModelFactory
import com.duoc.notar_ia.viewmodel.RegistroViewModelFactory

object Routes {
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val HOME = "home"
    const val PERFIL = "perfil"
}

@Composable
fun AppNavigation(
    usuarioRepository: UsuarioRepository,
    sessionManager: SessionManager
) {
    val navController = rememberNavController()

    val startDestination = if (sessionManager.isLoggedIn()) Routes.HOME else Routes.LOGIN

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                viewModel = viewModel(
                    factory = LoginViewModelFactory(usuarioRepository, sessionManager)
                ),
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
                    when (destino) {
                        "Perfil" -> navController.navigate(Routes.PERFIL)
                    }
                }
            )
        }
        composable(Routes.PERFIL) {
            PerfilScreen(
                nombre = sessionManager.getUserName(),
                correo = sessionManager.getUserEmail(),
                onEditarPerfil = { /* TODO: editar perfil */ },
                onCerrarSesion = {
                    sessionManager.clearSession()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onVolver = { navController.popBackStack() }
            )
        }
    }
}
