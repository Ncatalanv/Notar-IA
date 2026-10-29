
package com.duoc.notar_ia.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.Dorado
import com.duoc.notar_ia.ui.theme.FondoOscuro
import com.duoc.notar_ia.ui.theme.GrisMedio
import com.duoc.notar_ia.ui.theme.TextoClaro
import kotlinx.coroutines.delay

enum class LoginUiState {
    Idle,
    Loading,
    Success
}

@Composable
fun LoginStatusAnimation(
    state: LoginUiState,
    onSuccessAnimationFinished: () -> Unit
) {
    if (state == LoginUiState.Idle) return

    LaunchedEffect(state) {
        if (state == LoginUiState.Success) {
            delay(1000)
            onSuccessAnimationFinished()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoOscuro),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state == LoginUiState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(76.dp),
                    color = Dorado,
                    strokeWidth = 5.dp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Iniciando sesión...",
                    color = TextoClaro,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            if (state == LoginUiState.Success) {
                val escala = animateFloatAsState(
                    targetValue = 1f,
                    animationSpec = tween(450),
                    label = "checkScale"
                )

                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(
                        animationSpec = tween(300)
                    ) + scaleIn(
                        initialScale = 0.3f,
                        animationSpec = tween(450)
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .scale(escala.value)
                            .background(
                                color = GrisMedio,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "✓",
                            color = Dorado,
                            fontSize = 66.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "¡Inicio de sesión exitoso!",
                    color = Dorado,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Bienvenido a Notar-IA",
                    color = Color.LightGray,
                    fontSize = 15.sp
                )
            }
        }
    }
}
