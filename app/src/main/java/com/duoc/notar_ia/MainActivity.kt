package com.duoc.notar_ia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.duoc.notar_ia.ui.navigation.AppNavigation
import com.duoc.notar_ia.ui.screens.AntecedentesLegalesScreen
import com.duoc.notar_ia.ui.screens.HomeScreen
import com.duoc.notar_ia.ui.screens.LoginScreen
import com.duoc.notar_ia.ui.theme.NotariaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotariaTheme {
                //AppNavigation()
                //HomeScreen()
                AntecedentesLegalesScreen()
            }
        }
    }
}