package com.duoc.notar_ia.remote.dto

import com.google.gson.annotations.SerializedName

data class FirmaDto(
    val id: Int,
    @SerializedName("tramite_id") val tramiteId: Int,
    @SerializedName("tipo_firma") val tipoFirma: String,
    val metodo: String,
    @SerializedName("created_at") val createdAt: String
)