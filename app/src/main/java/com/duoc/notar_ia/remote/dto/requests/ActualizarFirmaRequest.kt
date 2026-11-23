package com.duoc.notar_ia.remote.dto.requests

import com.google.gson.annotations.SerializedName

data class ActualizarFirmaRequest(
    @SerializedName("tipo_firma") val tipoFirma: String? = null,
    val metodo: String? = null
)