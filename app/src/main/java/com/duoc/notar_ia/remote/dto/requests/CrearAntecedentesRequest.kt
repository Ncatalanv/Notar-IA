package com.duoc.notar_ia.remote.dto.requests

import com.google.gson.annotations.SerializedName

data class CrearAntecedentesRequest(
    @SerializedName("tramite_id") val tramiteId: Int,
    val nombre: String,
    val rut: String,
    val domicilio: String,
    val nacionalidad: String,
    val profesion: String
)