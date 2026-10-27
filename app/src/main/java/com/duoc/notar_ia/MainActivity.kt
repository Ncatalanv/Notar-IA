package com.duoc.notar_ia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.duoc.notar_ia.ui.navigation.AppNavigation
import com.duoc.notar_ia.ui.theme.NotariaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val application = application as NotariaApplication
        val usuarioRepository = application.usuarioRepository
        val sessionManager = application.sessionManager

        setContent {
            NotariaTheme {
                AppNavigation(
                    usuarioRepository = usuarioRepository,
                    sessionManager = sessionManager
                )
            }
        }
    }
}
