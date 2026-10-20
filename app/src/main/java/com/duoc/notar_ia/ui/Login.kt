package com.duoc.notar_ia.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.duoc.notar_ia.R

@Composable
fun Login(){

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var modal by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()    //Para que el logo no quede tapado con la barra sup.
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_notar_ia),
                        contentDescription = "Logo de Notar-IA",
                        modifier = Modifier
                            .width(400.dp)
                            .height(325.dp)
                    )

                    Text(
                        text = "Documentos notariales con validez legal desde tu celular",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(Modifier.height(48.dp))

                Text(text = "Nombre")

                OutlinedTextField(
                    value = username,
                    onValueChange = { nuevoTexto ->
                        username = nuevoTexto
                    },
                    label = { Text("Introduce tu nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Contraseña")

                OutlinedTextField(
                    value = password,
                    onValueChange = { nuevoTexto ->
                        password = nuevoTexto
                    },
                    label = { Text("Introduce tu contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(42.dp))

                Button(
                    onClick = { modal = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Enviar")
                }

                if (modal) {
                    AlertDialog(
                        onDismissRequest = { modal = false },
                        title = { Text("Mensaje") },
                        text = { Text("Se debe crear el usuario: $username con la contraseña:$password") },
                        confirmButton = {
                            Button(onClick = { modal = false }) {
                                Text("OK")
                            }
                        }
                    )
                }

            }

    }
}