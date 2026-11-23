package com.duoc.notar_ia.remote.dto

import com.google.gson.annotations.SerializedName

data class VerificacionDto(
    val id: Int,
    @SerializedName("tramite_id") val tramiteId: Int,
    @SerializedName("usuario_id") val usuarioId: Int,
    val verificado: Boolean,
    @SerializedName("foto_url") val fotoUrl: String?,
    @SerializedName("fecha_verificacion") val fechaVerificacion: String?,
    @SerializedName("created_at") val createdAt: String
)