package com.duoc.notar_ia.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.duoc.notar_ia.ui.theme.Dorado
import com.duoc.notar_ia.ui.theme.FondoOscuro
import com.duoc.notar_ia.ui.theme.GrisMedio
import com.duoc.notar_ia.ui.theme.TextoClaro

private enum class FaceCaptureState {
    Idle,
    Capturing,
    Detected,
    Error
}

@Composable
fun FaceCaptureScreen(
    onRostroDetectado: () -> Unit = {},
    onVolver: () -> Unit = {}
) {
    val context = LocalContext.current
    val activity = context as ComponentActivity

    var state by remember { mutableStateOf(FaceCaptureState.Idle) }
    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }
    var permissionDenied by remember { mutableStateOf(false) }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            capturedImage = bitmap
            state = FaceCaptureState.Detected
        } else {
            state = FaceCaptureState.Error
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            permissionDenied = false
            state = FaceCaptureState.Capturing
            cameraLauncher.launch(null)
        } else {
            permissionDenied = true
            state = FaceCaptureState.Idle
        }
    }

    fun launchCameraFlow() {
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                permissionDenied = false
                state = FaceCaptureState.Capturing
                cameraLauncher.launch(null)
            }

            activity.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                permissionDenied = true
            }

            else -> {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoOscuro)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "NOTAR-IA",
            color = Dorado,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(Modifier.height(28.dp))

        Text(
            text = "Verificación facial",
            color = Dorado,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Centra tu rostro dentro del encuadre y toma una foto.",
            color = TextoClaro,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(GrisMedio, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                FaceCaptureState.Capturing -> {
                    CircularProgressIndicator(color = Dorado)
                }

                FaceCaptureState.Detected -> {
                    capturedImage?.let { bitmap ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "Foto capturada",
                                modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .aspectRatio(3f / 4f)
                                    .background(FondoOscuro, RoundedCornerShape(12.dp))
                            )

                            Spacer(Modifier.height(16.dp))

                            Text(
                                text = "Rostro detectado",
                                color = Dorado,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                FaceCaptureState.Error -> {
                    Text(
                        text = "No se pudo capturar la foto. Intenta de nuevo.",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(24.dp)
                    )
                }

                else -> {
                    Text(
                        text = "☻",
                        color = Dorado,
                        fontSize = 90.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        if (permissionDenied) {
            Text(
                text = "Se necesita permiso de cámara para continuar.",
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        when (state) {
            FaceCaptureState.Detected -> {
                Button(
                    onClick = onRostroDetectado,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Dorado,
                        contentColor = FondoOscuro
                    )
                ) {
                    Text(
                        text = "Continuar",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        capturedImage = null
                        state = FaceCaptureState.Idle
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Dorado)
                ) {
                    Text("Tomar otra foto")
                }
            }

            else -> {
                Button(
                    onClick = ::launchCameraFlow,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Dorado,
                        contentColor = FondoOscuro
                    )
                ) {
                    Text(
                        text = if (state == FaceCaptureState.Error) "Reintentar" else "Tomar foto",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        TextButton(onClick = onVolver) {
            Text(
                text = "‹ Volver",
                color = Dorado,
                fontSize = 16.sp
            )
        }
    }
}
