package com.duoc.notar_ia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.duoc.notar_ia.ui.screens.LoginScreen
import com.duoc.notar_ia.ui.theme.NotariaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotariaTheme {
                // Sin argumentos: la pantalla obtiene el ViewModel del ciclo de vida del Activity
                LoginScreen()
            }
        }
    }
}