package com.duoc.notar_ia.remote.dto.requests

data class ActualizarTramiteRequest(
    val nombre: String? = null,
    val categoria: String? = null,
    val estado: String? = null,
    val precio: Double? = null
)