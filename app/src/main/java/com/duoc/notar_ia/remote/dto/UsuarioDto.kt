package com.duoc.notar_ia.remote.dto

import com.google.gson.annotations.SerializedName

data class UsuarioDto(
    val id: Int,
    val nombre: String,
    val correo: String,
    val telefono: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("created_at") val createdAt: String
)