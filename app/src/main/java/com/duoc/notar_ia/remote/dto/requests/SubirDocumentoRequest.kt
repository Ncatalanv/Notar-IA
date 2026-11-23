package com.duoc.notar_ia.remote.dto.requests

import com.google.gson.annotations.SerializedName

data class SubirDocumentoRequest(
    @SerializedName("tramite_id") val tramiteId: Int,
    @SerializedName("nombre_archivo") val nombreArchivo: String,
    @SerializedName("url_archivo") val urlArchivo: String,
    val tipo: String
)