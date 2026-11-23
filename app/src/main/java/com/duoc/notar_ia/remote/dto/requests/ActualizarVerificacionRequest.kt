package com.duoc.notar_ia.remote.dto.requests

import com.google.gson.annotations.SerializedName

data class ActualizarVerificacionRequest(
    val verificado: Boolean? = null,
    @SerializedName("foto_url") val fotoUrl: String? = null
)