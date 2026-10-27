package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.*

@Composable
fun DetalleNotariaScreen(
    nombre: String = "Notaría Central",
    onSeleccionar: () -> Unit = {},
    onVolver: () -> Unit = {}
) {
    Column(
        Modifier.fillMaxSize().background(FondoOscuro).padding(20.dp)
    ) {
        Text("NOTAR-IA", color = Dorado)
        Spacer(Modifier.height(28.dp))
        Text(nombre, color = Dorado, fontSize = 27.sp)

        Spacer(Modifier.height(20.dp))
        Card(
            Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = GrisMedio)
        ) {
            Column(Modifier.padding(18.dp)) {
                Box(
                    Modifier.fillMaxWidth().height(120.dp).background(FondoOscuro),
                    contentAlignment = Alignment.Center
                ) {
                    Text("⌖", color = Dorado, fontSize = 48.sp)
                }

                Spacer(Modifier.height(18.dp))
                Text("Dirección", color = Dorado)
                Text("Por confirmar", color = TextoClaro)

                Spacer(Modifier.height(12.dp))
                Text("Horario", color = Dorado)
                Text("Consultar disponibilidad", color = TextoClaro)

                Spacer(Modifier.height(12.dp))
                Text("Contacto", color = Dorado)
                Text("Por confirmar", color = TextoClaro)
            }
        }

        Spacer(Modifier.height(20.dp))
        Text(
            "Confirma la dirección y los horarios con la notaría antes de acudir.",
            color = TextoClaro,
            fontSize = 13.sp
        )

        Spacer(Modifier.height(22.dp))
        Button(
            onClick = onSeleccionar,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Dorado)
        ) {
            Text("Seleccionar notaría", color = FondoOscuro)
        }

        Spacer(Modifier.height(10.dp))
        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Dorado)
        ) {
            Text("Volver al listado")
        }
    }
}
