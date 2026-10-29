package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import com.duoc.notar_ia.R
import com.duoc.notar_ia.ui.theme.NotariaTheme
import com.duoc.notar_ia.ui.theme.Dorado
import com.duoc.notar_ia.ui.theme.GrisMedio
import com.duoc.notar_ia.viewmodel.LoginViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.TextButton
import com.duoc.notar_ia.ui.screens.LoginStatusAnimation
import com.duoc.notar_ia.ui.screens.LoginAnimationState

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateToRegistro: () -> Unit = {},
    onLoginExitoso: () -> Unit = {}
){

    // 1) Observar el estado primero. Cada vez que ViewModel lo cambie, esta función se ejecuta sola
    val uiState by viewModel.uiState.collectAsState()
    //var username by remember { mutableStateOf("") } --> reemplazada por uiState.correo
    //var password by remember { mutableStateOf("") } --> reemplazada por uiState.contrasena
    //var modal by remember { mutableStateOf(false) } --> reemplazada por uiState.mostrarModal

    // 2) El toggle de ver la contraseña (estado visual)
    // rememberSaveable para que sobreviva al rotar el celular
    var passwordVisible by rememberSaveable { mutableStateOf(false)} //Estado de contraseña

    // Colores compartidos por los campos del formulario
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
        Box(modifier = Modifier.fillMaxSize()) {
        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()    //Para que el logo no quede tapado con la barra sup.
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_notar_ia),
                        contentDescription = "Logo de Notar-IA",
                        modifier = Modifier
                            .width(220.dp)
                            .height(180.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Documentos notariales con validez legal desde tu celular",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center       //Centrar el slogan
                    )

                Spacer(Modifier.height(48.dp))

                OutlinedTextField(
                    value = uiState.correo,
                    onValueChange = { nuevoTexto ->
                        viewModel.onCorreoChange(nuevoTexto)
                    },
                    label = { Text("Correo electrónico") },
                    leadingIcon = {
                        Icon(  //Icono de correo en campo de correo
                            imageVector = Icons.Filled.Email,
                            contentDescription = "Icono de correo"
                        )
                    },
                    isError = uiState.intentoEnvio && uiState.errorCorreo != null,
                    supportingText = {
                        // ?.let --> si no es null, haz esto con el valor
                        uiState.errorCorreo?.let { mensaje ->
                            Text(mensaje, color = MaterialTheme.colorScheme.error)
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = coloresCampos,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = uiState.contrasena,
                    onValueChange = { nuevoTexto ->
                        viewModel.onContrasenaChange(nuevoTexto)
                    },
                    label = { Text("Introduce tu contraseña") },
                    leadingIcon = {  //leadingIcon --> icono al inicio (izquierda)
                        Icon(  //Icono de candado para campo de contraseña
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Icono de candado"
                        )
                    },
                    isError = uiState.intentoEnvio && uiState.errorContrasena != null,
                    supportingText = {
                        uiState.errorContrasena?.let { mensaje ->
                            Text(mensaje, color = MaterialTheme.colorScheme.error)
                        }
                    },
                    colors = coloresCampos,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible}) {
                            Icon(
                                imageVector = if(passwordVisible) Icons.Filled.Visibility
                                                else Icons.Filled.VisibilityOff,
                                contentDescription = if(passwordVisible) "Ocultar contraseña"
                                                        else "Mostrar contraseña"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None
                                            else PasswordVisualTransformation(),  // texto --> *****
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password) // le avisa al teclado que es una contraseña
                )

                TextButton(
                    onClick = onNavigateToRegistro,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "¿No tienes cuenta? Regístrate aquí",
                        color = Dorado,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { viewModel.onEnviar() },
                    // Deshabilitado solo si el formulario está vacío
                    enabled = uiState.correo.isNotBlank() && uiState.contrasena.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Ingresar")
                }

                if (uiState.mostrarModal) {
                    AlertDialog(
                        onDismissRequest = { viewModel.onCerrarModal() },
                        containerColor = GrisMedio,
                        title = {
                            Text(
                                text = if (uiState.loginExitoso) "Sesión Iniciada!" else "No se ha podido acceder...",
                                color = MaterialTheme.colorScheme.primary
                            ) },
                        text = {
                            Text(
                                if (uiState.loginExitoso)
                                    "Bienvenido, ${uiState.correo}. Has iniciado sesión correctamente."
                                else
                                    "Revise los campos marcados e intente nuevamente."
                            )
                        },
                        confirmButton = {
                            Button(onClick = {
                                viewModel.onCerrarModal()
                                if (uiState.loginExitoso) onLoginExitoso()
                            }) {
                                Text("OK")
                            }
                        }
                    )
                }

            }

            if (uiState.mostrarAnimacionExito) {
                LoginStatusAnimation(
                    state = LoginAnimationState.Success,
                    onSuccessAnimationFinished = {
                        viewModel.onAnimacionFinalizada()
                        onLoginExitoso()
                    }
                )
            }
        }
    }
}


