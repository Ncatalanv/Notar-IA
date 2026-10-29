package com.duoc.notar_ia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duoc.notar_ia.repository.UsuarioRepository
import com.duoc.notar_ia.ui.screens.AntecedentesLegalesScreen
import com.duoc.notar_ia.ui.screens.CargarDocumentoScreen
import com.duoc.notar_ia.ui.screens.ChatbotConversacionScreen
import com.duoc.notar_ia.ui.screens.CrearTramiteAsistenteScreen
import com.duoc.notar_ia.ui.screens.DetalleNotariaScreen
import com.duoc.notar_ia.ui.screens.FaceCaptureScreen
import com.duoc.notar_ia.ui.screens.HomeScreen
import com.duoc.notar_ia.ui.screens.LoginScreen
import com.duoc.notar_ia.ui.screens.MisTramitesScreen
import com.duoc.notar_ia.ui.screens.NotariasDisponiblesScreen
import com.duoc.notar_ia.ui.screens.PagoIdentidadScreen
import com.duoc.notar_ia.ui.screens.PerfilScreen
import com.duoc.notar_ia.ui.screens.RegistroScreen
import com.duoc.notar_ia.ui.screens.TipoFirmaMetodoScreen
import com.duoc.notar_ia.utils.SessionManager
import com.duoc.notar_ia.viewmodel.LoginViewModelFactory
import com.duoc.notar_ia.viewmodel.RegistroViewModelFactory

object Routes {
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val HOME = "home"
    const val PERFIL = "perfil"
    const val CREAR_TRAMITE = "crear_tramite"
    const val CARGAR_DOCUMENTO = "cargar_documento"
    const val TIPO_FIRMA = "tipo_firma"
    const val ANTECEDENTES_LEGALES = "antecedentes_legales"
    const val PAGO_IDENTIDAD = "pago_identidad"
    const val FACE_CAPTURE = "face_capture"
    const val NOTARIAS_DISPONIBLES = "notarias_disponibles"
    const val DETALLE_NOTARIA = "detalle_notaria/{nombre}"
    const val MIS_TRAMITES = "mis_tramites"
    const val CHATBOT = "chatbot"

    fun detalleNotaria(nombre: String) = "detalle_notaria/$nombre"
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
                onTramiteClick = { navController.navigate(Routes.CREAR_TRAMITE) },
                onVerNotarias = { navController.navigate(Routes.NOTARIAS_DISPONIBLES) },
                onNavegar = { destino ->
                    when (destino) {
                        "Inicio" -> navController.navigate(Routes.HOME) {
                            popUpTo(Routes.HOME) { inclusive = true }
                        }

                        "Trámite" -> navController.navigate(Routes.CREAR_TRAMITE)
                        "Firmas" -> navController.navigate(Routes.TIPO_FIRMA)
                        "Agente virtual" -> navController.navigate(Routes.CHATBOT)
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

        composable(Routes.CREAR_TRAMITE) {
            CrearTramiteAsistenteScreen(
                onContinuar = { navController.navigate(Routes.CARGAR_DOCUMENTO) },
                onVolver = {navController.popBackStack()}
            )
        }

        composable(Routes.CARGAR_DOCUMENTO) {
            CargarDocumentoScreen(
                onContinuar = { navController.navigate(Routes.TIPO_FIRMA) }
            )
        }

        composable(Routes.TIPO_FIRMA) {
            TipoFirmaMetodoScreen(
                onContinuar = { _, _ -> navController.navigate(Routes.ANTECEDENTES_LEGALES) }
            )
        }

        composable(Routes.ANTECEDENTES_LEGALES) {
            AntecedentesLegalesScreen(
                onContinuarPago = { navController.navigate(Routes.PAGO_IDENTIDAD) },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Routes.PAGO_IDENTIDAD) { backStackEntry ->
            val identidadVerificada by backStackEntry
                .savedStateHandle
                .getStateFlow("identidadVerificada", false)
                .collectAsState()

            PagoIdentidadScreen(
                identidadVerificada = identidadVerificada,
                onVerificarIdentidad = { navController.navigate(Routes.FACE_CAPTURE) },
                onPagar = {
                    navController.navigate(Routes.MIS_TRAMITES) {
                        popUpTo(Routes.HOME) { inclusive = false }
                    }
                }
            )
        }

        composable(Routes.FACE_CAPTURE) {
            FaceCaptureScreen(
                onRostroDetectado = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("identidadVerificada", true)
                    navController.popBackStack()
                },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Routes.NOTARIAS_DISPONIBLES) {
            NotariasDisponiblesScreen(
                onSeleccionar = { nombre ->
                    navController.navigate(Routes.detalleNotaria(nombre))
                },
                onVolver = {navController.popBackStack()}
            )
        }

        composable(Routes.DETALLE_NOTARIA) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: "Notaría"
            DetalleNotariaScreen(
                nombre = nombre,
                onSeleccionar = { navController.popBackStack() },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Routes.MIS_TRAMITES) {
            MisTramitesScreen(
                onAbrir = { /* TODO: abrir detalle del trámite */ }
            )
        }

        composable(Routes.CHATBOT) {
            ChatbotConversacionScreen()
        }
    }
}
