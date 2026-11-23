package com.duoc.notar_ia.remote.dto

import com.google.gson.annotations.SerializedName

data class MensajeChatbotDto(
    val id: Int,
    @SerializedName("usuario_id") val usuarioId: Int?,
    val autor: String,
    val texto: String,
    @SerializedName("created_at") val createdAt: String
)