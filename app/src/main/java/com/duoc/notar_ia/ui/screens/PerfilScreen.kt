
package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.Dorado
import com.duoc.notar_ia.ui.theme.FondoOscuro
import com.duoc.notar_ia.ui.theme.GrisMedio
import com.duoc.notar_ia.ui.theme.TextoClaro

@Composable
fun PerfilScreen(
    nombre: String = "Usuario Notar-IA",
    correo: String = "usuario@ejemplo.cl",
    onEditarPerfil: () -> Unit = {},
    onCerrarSesion: () -> Unit = {},
    onVolver: () -> Unit = {}
) {
    var mostrarConfirmacion by remember {
        mutableStateOf(false)
    }

    Scaffold(
        containerColor = FondoOscuro
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FondoOscuro)
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Encabezado
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

                Text(
                    text = "MI PERFIL",
                    color = TextoClaro,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.weight(1f))

                // Equilibra el encabezado
                Box(modifier = Modifier.size(64.dp))
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Avatar del usuario
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Dorado, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                val iniciales = nombre
                    .trim()
                    .split(Regex("\\s+"))
                    .filter { it.isNotBlank() }
                    .take(2)
                    .joinToString("") { it.first().uppercase() }

                Text(
                    text = iniciales.ifBlank { "U" },
                    color = FondoOscuro,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = nombre,
                color = TextoClaro,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = correo,
                color = Dorado,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Tarjeta con los datos del usuario
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = GrisMedio
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Información de la cuenta",
                        color = Dorado,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Nombre completo",
                        color = Color.LightGray,
                        fontSize = 13.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = nombre,
                        color = TextoClaro,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider(
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Correo electrónico",
                        color = Color.LightGray,
                        fontSize = 13.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = correo,
                        color = TextoClaro,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Opción para editar el perfil
            Button(
                onClick = onEditarPerfil,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Dorado,
                    contentColor = FondoOscuro
                )
            ) {
                Text(
                    text = "✎  Editar perfil",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Opción para cerrar sesión
            OutlinedButton(
                onClick = { mostrarConfirmacion = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Dorado
                )
            ) {
                Text(
                    text = "↪  Cerrar sesión",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Notar-IA · Tu confianza, nuestra prioridad",
                color = Color.Gray,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
    }

    // Confirmación antes de cerrar sesión
    if (mostrarConfirmacion) {
        AlertDialog(
            onDismissRequest = {
                mostrarConfirmacion = false
            },
            containerColor = GrisMedio,
            title = {
                Text(
                    text = "Cerrar sesión",
                    color = Dorado,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "¿Estás seguro de que deseas cerrar tu sesión?",
                    color = TextoClaro
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarConfirmacion = false
                        onCerrarSesion()
                    }
                ) {
                    Text(
                        text = "Sí, cerrar sesión",
                        color = Dorado
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarConfirmacion = false
                    }
                ) {
                    Text(
                        text = "Cancelar",
                        color = TextoClaro
                    )
                }
            }
        )
    }
}
