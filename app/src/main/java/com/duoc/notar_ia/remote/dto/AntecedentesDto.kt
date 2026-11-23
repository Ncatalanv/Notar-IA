package com.duoc.notar_ia.remote.dto

import com.google.gson.annotations.SerializedName

data class AntecedentesDto(
    val id: Int,
    @SerializedName("tramite_id") val tramiteId: Int,
    val nombre: String,
    val rut: String,
    val domicilio: String,
    val nacionalidad: String,
    val profesion: String,
    @SerializedName("created_at") val createdAt: String
)