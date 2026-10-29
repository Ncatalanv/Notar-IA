package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.*

@Composable
fun PagoIdentidadScreen(
    identidadVerificada: Boolean = false,
    onVerificarIdentidad: () -> Unit = {},
    onPagar: () -> Unit = {}
) {
    var identidad by remember { mutableStateOf(identidadVerificada) }
    var aceptar by remember { mutableStateOf(false) }

    // Si volvemos de FaceCaptureScreen con éxito, se marca la identidad como verificada
    LaunchedEffect(identidadVerificada) {
        identidad = identidadVerificada
    }

    Column(
        Modifier.fillMaxSize().background(FondoOscuro).padding(20.dp)
    ) {
        Text("NOTAR-IA", color = Dorado)
        Spacer(Modifier.height(28.dp))
        Text("Pago e identidad", color = Dorado, fontSize = 27.sp)
        Spacer(Modifier.height(20.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = GrisMedio)
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("Resumen del trámite", color = Dorado, fontSize = 18.sp)
                Spacer(Modifier.height(12.dp))
                Text("Trámite: Declaración Jurada", color = TextoClaro)
                Text("Firma: Por confirmar", color = TextoClaro)
                Text("Método: Por confirmar", color = TextoClaro)
                Spacer(Modifier.height(12.dp))
                Text("Total referencial: \$8.000", color = Dorado, fontSize = 18.sp)
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Verificación de identidad", color = Dorado, fontSize = 18.sp)

        if (identidad) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = true,
                    onCheckedChange = null,
                    colors = CheckboxDefaults.colors(checkedColor = Dorado)
                )
                Text("Identidad verificada", color = TextoClaro)
            }
        } else {
            Button(
                onClick = onVerificarIdentidad,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Dorado)
            ) {
                Text("Verificar identidad con cámara", color = FondoOscuro)
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = aceptar,
                onCheckedChange = { aceptar = it },
                colors = CheckboxDefaults.colors(checkedColor = Dorado)
            )
            Text("Confirmo los datos del trámite", color = TextoClaro)
        }

        Spacer(Modifier.height(20.dp))
        Button(
            onClick = { if (identidad && aceptar) onPagar() },
            enabled = identidad && aceptar,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Dorado)
        ) {
            Text("Continuar al pago", color = FondoOscuro)
        }

        Spacer(Modifier.height(10.dp))
        Text(
            "El pago y la verificación reales requieren integración con servicios seguros.",
            color = TextoClaro,
            fontSize = 11.sp
        )
    }
}
