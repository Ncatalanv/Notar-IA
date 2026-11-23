package com.duoc.notar_ia.ui.screens

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.*

/** Obtiene el nombre real del archivo desde el content resolver del sistema. */
private fun Uri.nombreArchivo(context: Context): String {
    context.contentResolver
        .query(this, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
        ?.use { cursor ->
            val indice = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (indice >= 0 && cursor.moveToFirst()) {
                val nombre = cursor.getString(indice)
                if (!nombre.isNullOrBlank()) return nombre
            }
        }
    return lastPathSegment ?: "documento"
}

@Composable
fun CargarDocumentoScreen(onContinuar: (String) -> Unit = {}) {
    val context = LocalContext.current
    var archivo by remember { mutableStateOf("") }
    var uriSeleccionado by remember { mutableStateOf<Uri?>(null) }
    var error by remember { mutableStateOf(false) }

    // Recurso nativo: selector de archivos del dispositivo (Storage Access Framework).
    val selectorArchivo = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            uriSeleccionado = uri
            archivo = uri.nombreArchivo(context)
            error = false
        }
    }

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
            Modifier
                .fillMaxWidth()
                .height(170.dp)
                .clickable { selectorArchivo.launch("*/*") },
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
                Text("Toca para elegir un archivo", color = TextoClaro, fontSize = 11.sp)
            }
        }

        Spacer(Modifier.height(12.dp))
        OutlinedButton(
            onClick = { selectorArchivo.launch("*/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Seleccionar archivo del dispositivo")
        }

        if (uriSeleccionado != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                "Archivo adjunto: $archivo",
                color = Dorado,
                fontSize = 13.sp
            )
        }

        Spacer(Modifier.height(18.dp))
        OutlinedTextField(
            value = archivo,
            onValueChange = {
                archivo = it
                uriSeleccionado = null
                error = false
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nombre del archivo") },
            placeholder = { Text("documento.pdf") },
            supportingText = { Text("También puedes escribirlo manualmente.") },
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
