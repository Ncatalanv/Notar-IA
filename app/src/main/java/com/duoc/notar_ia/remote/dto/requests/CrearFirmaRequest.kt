package com.duoc.notar_ia.remote.dto.requests

import com.google.gson.annotations.SerializedName

data class CrearFirmaRequest(
    @SerializedName("tramite_id") val tramiteId: Int,
    @SerializedName("tipo_firma") val tipoFirma: String,
    val metodo: String
)