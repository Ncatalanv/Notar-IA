package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.*

@Composable
fun AntecedentesLegalesScreen(
    onContinuarPago: () -> Unit = {},
    onVolver: () -> Unit = {}
) {
    var nombre by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var domicilio by remember { mutableStateOf("") }
    var nacionalidad by remember { mutableStateOf("") }
    var profesion by remember { mutableStateOf("") }
    var mostrarError by remember { mutableStateOf(false) }

    val camposCompletos = nombre.isNotBlank() &&
            rut.isNotBlank() &&
            domicilio.isNotBlank() &&
            nacionalidad.isNotBlank() &&
            profesion.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoOscuro)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        TextButton(onClick = onVolver) {
            Text("← Volver", color = Dorado, fontSize = 16.sp)
        }

        Spacer(Modifier.height(12.dp))

        Text(
            "Notar-IA",
            color = Dorado,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "NOTARÍA DIGITAL",
            color = TextoClaro,
            fontSize = 11.sp,
            letterSpacing = 3.sp
        )

        Spacer(Modifier.height(30.dp))

        Text(
            "Completa tus\nantecedentes legales",
            color = Dorado,
            fontSize = 27.sp,
            lineHeight = 33.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        Text(
            "Necesitamos esta información para validar tu identidad y continuar con el proceso notarial.",
            color = TextoClaro,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )

        Spacer(Modifier.height(22.dp))

        CampoAntecedente(
            etiqueta = "Nombre completo",
            valor = nombre,
            onValorChange = { nombre = it },
            autocompletado = true
        )

        CampoAntecedente(
            etiqueta = "RUT",
            valor = rut,
            onValorChange = { rut = it },
            autocompletado = true
        )

        CampoAntecedente(
            etiqueta = "Domicilio",
            valor = domicilio,
            onValorChange = { domicilio = it },
            autocompletado = true
        )

        CampoAntecedente(
            etiqueta = "Nacionalidad",
            valor = nacionalidad,
            onValorChange = { nacionalidad = it },
            autocompletado = true
        )

        CampoAntecedente(
            etiqueta = "Profesión u oficio",
            valor = profesion,
            onValorChange = { profesion = it },
            autocompletado = false
        )

        if (mostrarError && !camposCompletos) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 16.dp),
                color = GrisMedio,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    "⚠ Prevalidación: completa todos los campos.",
                    color = Dorado,
                    modifier = Modifier.padding(14.dp),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            Spacer(Modifier.height(16.dp))
        }

        Button(
            onClick = {
                mostrarError = true
                if (camposCompletos) {
                    onContinuarPago()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Dorado,
                contentColor = FondoOscuro
            )
        ) {
            Text(
                "Continuar al pago",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun CampoAntecedente(
    etiqueta: String,
    valor: String,
    onValorChange: (String) -> Unit,
    autocompletado: Boolean
) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        OutlinedTextField(
            value = valor,
            onValueChange = onValorChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    etiqueta,
                    color = Color(0xFF686B73),
                    fontWeight = FontWeight.Medium
                )
            },
            trailingIcon = {
                Text(
                    if (autocompletado) "✓ IA" else "!",
                    color = if (autocompletado) Color(0xFF26865B)
                    else Dorado,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(9.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Dorado,
                unfocusedBorderColor = GrisMedio,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = Dorado
            )
        )

        if (autocompletado) {
            Text(
                "Autocompletado por IA",
                modifier = Modifier.padding(start = 6.dp, top = 3.dp),
                color = TextoClaro,
                fontSize = 10.sp
            )
        }
    }
}
