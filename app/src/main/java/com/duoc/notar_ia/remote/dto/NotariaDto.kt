package com.duoc.notar_ia.remote.dto

data class NotariaDto(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val comuna: String,
    val horario: String?,
    val contacto: String?,
    val latitud: Double?,
    val longitud: Double?
)