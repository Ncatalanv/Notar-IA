package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.duoc.notar_ia.R
import com.duoc.notar_ia.ui.theme.Dorado
import com.duoc.notar_ia.ui.theme.GrisMedio
import com.duoc.notar_ia.ui.theme.NotariaTheme
import com.duoc.notar_ia.viewmodel.RegistroViewModel

@Composable
fun RegistroScreen(
    viewModel: RegistroViewModel = viewModel(),
    onNavigateBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    var passVisible by rememberSaveable { mutableStateOf(false) }
    var confirmVisible by rememberSaveable { mutableStateOf(false) }

    val coloresCampos = OutlinedTextFieldDefaults.colors(
        unfocusedBorderColor = Dorado.copy(alpha = 0.5f),
        unfocusedLabelColor = Dorado.copy(alpha = 0.7f),
        focusedContainerColor = GrisMedio,
        unfocusedContainerColor = GrisMedio,
        focusedLeadingIconColor = Dorado,
        unfocusedLeadingIconColor = Dorado.copy(alpha = 0.7f)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_notar_ia),
                contentDescription = "Logo de Notar-IA",
                modifier = Modifier
                    .width(180.dp)
                    .height(140.dp)
            )

            Text(
                text = "Crea tu cuenta",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = uiState.nombre,
                onValueChange = { viewModel.onNombreChange(it) },
                label = { Text("Nombre completo") },
                leadingIcon = {
                    Icon(Icons.Filled.Person, contentDescription = "Icono de usuario")
                },
                isError = uiState.intentoEnvio && uiState.errorNombre != null,
                supportingText = {
                    uiState.errorNombre?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                colors = coloresCampos,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.correo,
                onValueChange = { viewModel.onCorreoChange(it) },
                label = { Text("Correo electrónico") },
                leadingIcon = {
                    Icon(Icons.Filled.Email, contentDescription = "Icono de correo")
                },
                isError = uiState.intentoEnvio && uiState.errorCorreo != null,
                supportingText = {
                    uiState.errorCorreo?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = coloresCampos,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.contrasena,
                onValueChange = { viewModel.onContrasenaChange(it) },
                label = { Text("Contraseña") },
                leadingIcon = {
                    Icon(Icons.Filled.Lock, contentDescription = "Icono de candado")
                },
                isError = uiState.intentoEnvio && uiState.errorContrasena != null,
                supportingText = {
                    uiState.errorContrasena?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { passVisible = !passVisible }) {
                        Icon(
                            imageVector = if (passVisible) Icons.Filled.Visibility
                                          else Icons.Filled.VisibilityOff,
                            contentDescription = if (passVisible) "Ocultar contraseña"
                                                 else "Mostrar contraseña"
                        )
                    }
                },
                visualTransformation = if (passVisible) VisualTransformation.None
                                       else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = coloresCampos,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.confirmarContrasena,
                onValueChange = { viewModel.onConfirmarChange(it) },
                label = { Text("Confirmar contraseña") },
                leadingIcon = {
                    Icon(Icons.Filled.Lock, contentDescription = "Icono de candado")
                },
                isError = uiState.intentoEnvio && uiState.errorConfirmarContrasena != null,
                supportingText = {
                    uiState.errorConfirmarContrasena?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { confirmVisible = !confirmVisible }) {
                        Icon(
                            imageVector = if (confirmVisible) Icons.Filled.Visibility
                                          else Icons.Filled.VisibilityOff,
                            contentDescription = if (confirmVisible) "Ocultar contraseña"
                                                 else "Mostrar contraseña"
                        )
                    }
                },
                visualTransformation = if (confirmVisible) VisualTransformation.None
                                       else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = coloresCampos,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = { viewModel.onRegistrar() },
                enabled = uiState.nombre.isNotBlank() && uiState.correo.isNotBlank() &&
                          uiState.contrasena.isNotBlank() && uiState.confirmarContrasena.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Registrarse")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "¿Ya tienes una cuenta?",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )
                TextButton(onClick = onNavigateBack) {
                    Text(text = "Inicia sesión", color = Dorado)
                }
            }

            if (uiState.mostrarModal) {
                AlertDialog(
                    onDismissRequest = { viewModel.onCerrarModal() },
                    containerColor = GrisMedio,
                    title = {
                        Text(
                            text = if (uiState.registroExitoso) "Cuenta creada!"
                                   else "No se pudo registrar...",
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    text = {
                        Text(
                            if (uiState.registroExitoso)
                                "Bienvenido, ${uiState.nombre}. Tu cuenta fue creada correctamente."
                            else
                                "Revisa los campos marcados e intenta nuevamente."
                        )
                    },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.onCerrarModal()
                            if (uiState.registroExitoso) onNavigateBack()
                        }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Registro normal")
@Composable
fun RegistroScreenPreview() {
    NotariaTheme {
        RegistroScreen(viewModel = RegistroViewModel())
    }
}
