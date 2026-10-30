package com.duoc.notar_ia.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.R
import com.duoc.notar_ia.ui.theme.Dorado
import com.duoc.notar_ia.ui.theme.FondoOscuro
import com.duoc.notar_ia.ui.theme.GrisMedio
import com.duoc.notar_ia.ui.theme.TextoClaro

/**
 * Pantalla que se muestra mientras se procesa el inicio de sesión.
 * No decide cuándo aparece: eso lo controla el estado `estaCargando` del LoginViewModel.
 */
@Composable
fun PantallaCargaLogin() {
    // El mismo texto sirve para mostrarse en pantalla y para los lectores de pantalla
    val textoCarga = stringResource(R.string.login_cargando)

    // Rotación continua del arco dorado sobre el riel gris
    val transicion = rememberInfiniteTransition(label = "cargaLogin")
    val rotacion by transicion.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotacion"
    )
    // El arco crece y se encoge para dar sensación de avance
    val barrido by transicion.animateFloat(
        initialValue = 40f,
        targetValue = 280f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "barrido"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoOscuro)
            // mergeDescendants evita que TalkBack lea el mensaje dos veces
            .semantics(mergeDescendants = true) { contentDescription = textoCarga },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Canvas(modifier = Modifier.size(64.dp)) {
            val grosor = 4.dp.toPx()
            val inset = grosor / 2
            val tamanoArco = Size(size.width - grosor, size.height - grosor)

            drawArc(
                color = GrisMedio,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = tamanoArco,
                style = Stroke(width = grosor)
            )

            rotate(rotacion) {
                drawArc(
                    color = Dorado,
                    startAngle = -90f,
                    sweepAngle = barrido,
                    useCenter = false,
                    topLeft = Offset(inset, inset),
                    size = tamanoArco,
                    style = Stroke(width = grosor, cap = StrokeCap.Round)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = textoCarga,
            color = TextoClaro,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
