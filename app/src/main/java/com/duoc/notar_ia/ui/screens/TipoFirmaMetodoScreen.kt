package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.*

@Composable
fun TipoFirmaMetodoScreen(
    onContinuar: (String, String) -> Unit = { _, _ -> }
) {
    var firma by remember { mutableStateOf("Firma avanzada") }
    var metodo by remember { mutableStateOf("Remoto") }

    Column(
        Modifier.fillMaxSize().background(FondoOscuro).padding(20.dp)
    ) {
        Text("NOTAR-IA", color = Dorado)
        Spacer(Modifier.height(28.dp))
        Text("Tipo de firma y método", color = Dorado, fontSize = 26.sp)

        Spacer(Modifier.height(20.dp))
        Text("Selecciona el tipo de firma", color = TextoClaro)

        listOf("Firma simple", "Firma avanzada", "Firma notariada").forEach {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                RadioButton(
                    selected = firma == it,
                    onClick = { firma = it },
                    colors = RadioButtonDefaults.colors(selectedColor = Dorado)
                )
                Text(it, color = TextoClaro)
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Método", color = TextoClaro)

        listOf("Remoto", "Presencial").forEach {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                RadioButton(
                    selected = metodo == it,
                    onClick = { metodo = it },
                    colors = RadioButtonDefaults.colors(selectedColor = Dorado)
                )
                Text(it, color = TextoClaro)
            }
        }

        Spacer(Modifier.height(25.dp))
        Button(
            onClick = { onContinuar(firma, metodo) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Dorado)
        ) {
            Text("Continuar", color = FondoOscuro)
        }
    }
}
