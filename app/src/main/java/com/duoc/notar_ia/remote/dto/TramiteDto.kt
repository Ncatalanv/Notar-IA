package com.duoc.notar_ia.remote.dto

import com.google.gson.annotations.SerializedName

data class TramiteDto(
    val id: Int,
    @SerializedName("usuario_id") val usuarioId: Int,
    val nombre: String,
    val categoria: String,
    val estado: String,
    val precio: Double,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)