package com.duoc.notar_ia.remote.dto.responses

import com.duoc.notar_ia.remote.dto.UsuarioDto
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val token: String?,
    val usuario: UsuarioDto?
)

data class SignupResponse(
    val success: Boolean,
    val message: String,
    @SerializedName("usuario_id") val usuarioId: Int?
)