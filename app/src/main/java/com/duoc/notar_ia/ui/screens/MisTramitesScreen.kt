package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.*

@Composable
fun MisTramitesScreen(onAbrir: (String) -> Unit = {}) {
    var filtro by remember { mutableStateOf("Todos") }

    val tramites = listOf(
        Triple("Declaración Jurada", "En revisión", "001"),
        Triple("Poder Especial", "Pendiente", "002"),
        Triple("Firma de Contrato", "Completado", "003")
    )

    Column(
        Modifier.fillMaxSize().background(FondoOscuro).padding(16.dp)
    ) {
        Text("NOTAR-IA", color = Dorado)
        Spacer(Modifier.height(24.dp))
        Text("Mis trámites", color = Dorado, fontSize = 28.sp)
        Text("Consulta el estado de tus solicitudes.", color = TextoClaro)

        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            listOf("Todos", "Pendiente", "En revisión", "Completado").forEach {
                FilterChip(
                    selected = filtro == it,
                    onClick = { filtro = it },
                    label = { Text(it, fontSize = 10.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Dorado,
                        selectedLabelColor = FondoOscuro
                    )
                )
            }
        }

        Spacer(Modifier.height(12.dp))
        tramites.filter { filtro == "Todos" || it.second == filtro }
            .forEach { (nombre, estado, id) ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onAbrir(id) },
                    colors = CardDefaults.cardColors(containerColor = GrisMedio)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Trámite N.º $id", color = Dorado, fontSize = 12.sp)
                        Text(nombre, color = TextoClaro, fontSize = 17.sp)
                        Spacer(Modifier.height(8.dp))
                        Text("Estado: $estado", color = Dorado)
                        Text("Ver detalle →", color = TextoClaro, fontSize = 12.sp)
                    }
                }
            }

        Spacer(Modifier.height(10.dp))
        Text("Datos de ejemplo; falta conectarlos a la base de datos.", color = TextoClaro, fontSize = 11.sp)
    }
}
