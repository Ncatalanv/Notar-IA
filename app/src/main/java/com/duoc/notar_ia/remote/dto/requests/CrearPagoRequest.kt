package com.duoc.notar_ia.remote.dto.requests

import com.google.gson.annotations.SerializedName

data class CrearPagoRequest(
    @SerializedName("tramite_id") val tramiteId: Int,
    val monto: Double,
    @SerializedName("metodo_pago") val metodoPago: String? = null
)