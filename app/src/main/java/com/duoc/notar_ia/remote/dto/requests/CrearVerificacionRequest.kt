package com.duoc.notar_ia.remote.dto.requests

import com.google.gson.annotations.SerializedName

data class CrearVerificacionRequest(
    @SerializedName("tramite_id") val tramiteId: Int,
    val verificado: Boolean,
    @SerializedName("foto_url") val fotoUrl: String? = null
)