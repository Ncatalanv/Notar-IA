package com.duoc.notar_ia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duoc.notar_ia.ui.screens.LoginScreen
import com.duoc.notar_ia.ui.screens.RegistroScreen

object Routes {
    const val LOGIN = "login"
    const val REGISTRO = "registro"
}

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ){
        composable(Routes.LOGIN){
            LoginScreen(
                onNavigateToRegistro = { navController.navigate(Routes.REGISTRO)}
            )
        }
        composable(Routes.REGISTRO){
            RegistroScreen(
                onNavigateBack = { navController.popBackStack()}
            )
        }
    }

}