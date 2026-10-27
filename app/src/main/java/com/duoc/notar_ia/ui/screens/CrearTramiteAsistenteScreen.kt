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
fun CrearTramiteAsistenteScreen(
    onContinuar: (String) -> Unit = {}
) {
    var consulta by remember { mutableStateOf("") }
    var mostrarOpciones by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize().background(FondoOscuro).padding(20.dp)
    ) {
        Text("NOTAR-IA", color = Dorado, fontSize = 14.sp)
        Spacer(Modifier.height(28.dp))
        Text("Crear trámite con asistente", color = Dorado, fontSize = 27.sp)
        Spacer(Modifier.height(10.dp))
        Text("Cuéntanos qué necesitas realizar.", color = TextoClaro)

        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = consulta,
            onValueChange = { consulta = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ej.: autorizar un viaje") },
            minLines = 3
        )

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { mostrarOpciones = consulta.isNotBlank() },
            colors = ButtonDefaults.buttonColors(containerColor = Dorado)
        ) {
            Text("Buscar trámite", color = FondoOscuro)
        }

        if (mostrarOpciones) {
            Spacer(Modifier.height(20.dp))
            Text("Trámites sugeridos", color = Dorado, fontSize = 19.sp)

            listOf("Declaración Jurada", "Poder Especial", "Autorización de viaje")
                .forEach { tramite ->
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clickable { onContinuar(tramite) },
                        colors = CardDefaults.cardColors(containerColor = GrisMedio)
                    ) {
                        Text(
                            tramite,
                            Modifier.padding(18.dp),
                            color = TextoClaro
                        )
                    }
                }
        }
    }
}
