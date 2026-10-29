package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.*

@Composable
fun NotariasDisponiblesScreen(
    onSeleccionar: (String) -> Unit = {},
            onVolver: () -> Unit = {}
) {
    var busqueda by remember { mutableStateOf("") }

    val notarias = listOf(
        "Notaría Central — Santiago Centro",
        "Notaría Providencia — Providencia",
        "Notaría Las Condes — Las Condes"
    )

    Column(
        Modifier.fillMaxSize().background(FondoOscuro).padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onVolver) {
                Text(
                    text = "‹ Volver",
                    color = Dorado,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text("NOTAR-IA", color = Dorado)
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier.size(64.dp))
        }
        Spacer(Modifier.height(24.dp))


        Text("Notarías disponibles", color = Dorado, fontSize = 27.sp)
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Buscar por nombre o comuna") },
            singleLine = true
        )

        Spacer(Modifier.height(16.dp))

        notarias.filter { it.contains(busqueda, ignoreCase = true) }
            .forEach { notaria ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onSeleccionar(notaria) },
                    colors = CardDefaults.cardColors(containerColor = GrisMedio)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("⌖", color = Dorado, fontSize = 25.sp)
                        Text(notaria, color = TextoClaro, fontSize = 16.sp)
                        Spacer(Modifier.height(8.dp))
                        Text("Consultar información →", color = Dorado, fontSize = 12.sp)
                    }
                }
            }

        Spacer(Modifier.height(10.dp))
        Text(
            "Listado de ejemplo. Verifica los datos y horarios reales antes de acudir.",
            color = TextoClaro,
            fontSize = 11.sp
        )
    }
}
