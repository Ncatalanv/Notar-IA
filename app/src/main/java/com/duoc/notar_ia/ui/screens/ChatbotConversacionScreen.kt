package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.*

@Composable
fun ChatbotConversacionScreen() {
    var mensaje by remember { mutableStateOf("") }
    val mensajes = remember {
        mutableStateListOf(
            "Asistente" to "¡Hola! ¿Qué trámite necesitas realizar hoy?"
        )
    }

    Column(
        Modifier.fillMaxSize().background(FondoOscuro).padding(16.dp)
    ) {
        Text("NOTAR-IA", color = Dorado)
        Spacer(Modifier.height(20.dp))
        Text("Asistente virtual", color = Dorado, fontSize = 27.sp)
        Text("Puedes describir tu necesidad.", color = TextoClaro)

        Column(
            Modifier.weight(1f).fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp)
        ) {
            mensajes.forEach { (autor, texto) ->
                Card(
                    Modifier.fillMaxWidth().padding(vertical = 5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (autor == "Tú") {
                            Color(0xFF393124)
                        } else GrisMedio
                    )
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(autor, color = Dorado, fontSize = 12.sp)
                        Text(texto, color = TextoClaro)
                    }
                }
            }
        }

        OutlinedTextField(
            value = mensaje,
            onValueChange = { mensaje = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Escribe tu mensaje...") }
        )

        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                if (mensaje.isNotBlank()) {
                    mensajes.add("Tú" to mensaje)
                    mensajes.add(
                        "Asistente" to
                                "Gracias por tu consulta. Esta respuesta es de demostración; todavía no estoy conectado a una IA."
                    )
                    mensaje = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Dorado)
        ) {
            Text("Enviar", color = FondoOscuro)
        }
    }
}
