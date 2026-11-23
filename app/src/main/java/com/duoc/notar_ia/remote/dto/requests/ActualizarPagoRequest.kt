package com.duoc.notar_ia.remote.dto.requests

import com.google.gson.annotations.SerializedName

data class ActualizarPagoRequest(
    val estado: String? = null,
    @SerializedName("metodo_pago") val metodoPago: String? = null,
    @SerializedName("fecha_pago") val fechaPago: String? = null
)