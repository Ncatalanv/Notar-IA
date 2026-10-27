package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.*

@Composable
fun CargarDocumentoScreen(
    onContinuar: (String) -> Unit = {}
) {
    var archivo by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize().background(FondoOscuro).padding(20.dp)
    ) {
        Text("NOTAR-IA", color = Dorado)
        Spacer(Modifier.height(28.dp))
        Text("Cargar documento", color = Dorado, fontSize = 27.sp)
        Spacer(Modifier.height(10.dp))
        Text("Adjunta el documento de tu trámite.", color = TextoClaro)

        Spacer(Modifier.height(25.dp))
        Card(
            Modifier.fillMaxWidth().height(170.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = GrisMedio)
        ) {
            Column(
                Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("＋", color = Dorado, fontSize = 42.sp)
                Text("Documento PDF o imagen", color = TextoClaro)
                Text("Selecciona o escribe su nombre abajo", color = TextoClaro, fontSize = 11.sp)
            }
        }

        Spacer(Modifier.height(18.dp))
        OutlinedTextField(
            value = archivo,
            onValueChange = { archivo = it; error = false },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nombre del archivo") },
            placeholder = { Text("documento.pdf") },
            singleLine = true
        )

        if (error) {
            Text("Escribe el nombre del archivo para continuar.", color = Dorado)
        }

        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
                if (archivo.isNotBlank()) onContinuar(archivo)
                else error = true
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Dorado)
        ) {
            Text("Continuar", color = FondoOscuro)
        }
    }
}
