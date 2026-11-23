package com.duoc.notar_ia.remote.dto.requests

data class ActualizarAntecedentesRequest(
    val nombre: String? = null,
    val rut: String? = null,
    val domicilio: String? = null,
    val nacionalidad: String? = null,
    val profesion: String? = null
)