package com.duoc.notar_ia.remote.dto

import com.google.gson.annotations.SerializedName

data class PagoDto(
    val id: Int,
    @SerializedName("tramite_id") val tramiteId: Int,
    val monto: Double,
    val estado: String,
    @SerializedName("metodo_pago") val metodoPago: String?,
    @SerializedName("fecha_pago") val fechaPago: String?,
    @SerializedName("created_at") val createdAt: String
)