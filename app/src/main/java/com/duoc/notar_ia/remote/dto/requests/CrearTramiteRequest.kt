package com.duoc.notar_ia.remote.dto.requests

data class CrearTramiteRequest(
    val nombre: String,
    val categoria: String,
    val precio: Double? = null
)