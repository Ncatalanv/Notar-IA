package com.duoc.notar_ia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.notar_ia.ui.theme.*

private data class Tramite(
    val nombre: String,
    val categoria: String,
    val precio: String
)

@Composable
fun HomeScreen(
    onTramiteClick: (String) -> Unit = {},
    onVerNotarias: () -> Unit = {},
    onNavegar: (String) -> Unit = {}
) {
    var busqueda by remember { mutableStateOf("") }
    var categoriaSeleccionada by remember { mutableStateOf("Todos") }

    val categorias = listOf(
        "Todos", "Firma Electronica",
        "Declaracion", "Contrato", "Poderes"
    )

    val tramites = listOf(
        Tramite("Firma de Contrato", "Contrato", "Desde $8.000"),
        Tramite("Declaración Jurada", "Declaracion", "Desde $8.000"),
        Tramite("Poder Especial", "Poderes", "Desde $8.000"),
        Tramite("Firma Electrónica", "Firma Electronica", "Desde $8.000")
    )

    val tramitesFiltrados = tramites.filter {
        (categoriaSeleccionada == "Todos" ||
                it.categoria == categoriaSeleccionada) &&
                it.nombre.contains(busqueda, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 14.dp)
        ) {
            Spacer(Modifier.height(24.dp))

            // Buscador principal
            OutlinedTextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        "⌕  Buscar trámites...",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp
                    )
                },
                leadingIcon = {
                    Text("⌕", color = Color(0xFF75530A), fontSize = 32.sp)
                },
                singleLine = true,
                shape = RoundedCornerShape(40.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Dorado,
                    unfocusedContainerColor = Dorado,
                    focusedBorderColor = Dorado,
                    unfocusedBorderColor = Dorado,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(Modifier.height(20.dp))

            // Filtros por categoría
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categorias.forEach { categoria ->
                    val seleccionada = categoriaSeleccionada == categoria

                    Surface(
                        shape = RoundedCornerShape(22.dp),
                        color = if (seleccionada) Dorado else GrisMedio,
                        modifier = Modifier.clickable {
                            categoriaSeleccionada = categoria
                        }
                    ) {
                        Text(
                            text = categoria,
                            modifier = Modifier.padding(
                                horizontal = 14.dp,
                                vertical = 10.dp
                            ),
                            color = if (seleccionada) Color.Black else TextoClaro,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(30.dp))

            Text(
                "Trámites disponibles",
                color = Dorado,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(14.dp))

            tramitesFiltrados.forEach { tramite ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp)
                        .clickable { onTramiteClick(tramite.nombre) },
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF1E5BD)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(58.dp)
                                .background(
                                    Color.Black,
                                    RoundedCornerShape(6.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("▤", color = Dorado, fontSize = 34.sp)
                        }

                        Spacer(Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                tramite.nombre,
                                color = Color.Black,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(Modifier.height(8.dp))

                            Surface(
                                color = Color.Black,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    tramite.precio,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(
                                        horizontal = 10.dp,
                                        vertical = 5.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }

            if (tramitesFiltrados.isEmpty()) {
                Text(
                    "No se encontraron trámites.",
                    color = TextoClaro,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            Text(
                "Notarías cercanas",
                color = Dorado,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(14.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
                    .clickable { onVerNotarias() },
                shape = RoundedCornerShape(5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = GrisMedio
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(58.dp)
                            .background(
                                Color.Black,
                                RoundedCornerShape(6.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("⌖", color = Dorado, fontSize = 34.sp)
                    }

                    Spacer(Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Buscar notarías disponibles",
                            color = TextoClaro,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            "Encuentra notarías presenciales cerca de ti",
                            color = TextoClaro,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        // Navegación inferior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF302522))
                .navigationBarsPadding()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf(
                "⌂" to "Inicio",
                "▤" to "Trámite",
                "✍" to "Firmas",
                "☻" to "Agente virtual",
                "●" to "Perfil"
            ).forEach { (icono, nombre) ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onNavegar(nombre) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(icono, color = Dorado, fontSize = 25.sp)
                    Text(
                        nombre,
                        color = TextoClaro,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}
