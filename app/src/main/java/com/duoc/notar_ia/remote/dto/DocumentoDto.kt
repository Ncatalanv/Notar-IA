package com.duoc.notar_ia.remote.dto

import com.google.gson.annotations.SerializedName

data class DocumentoDto(
    val id: Int,
    @SerializedName("tramite_id") val tramiteId: Int,
    @SerializedName("nombre_archivo") val nombreArchivo: String,
    @SerializedName("url_archivo") val urlArchivo: String,
    val tipo: String,
    @SerializedName("created_at") val createdAt: String
)