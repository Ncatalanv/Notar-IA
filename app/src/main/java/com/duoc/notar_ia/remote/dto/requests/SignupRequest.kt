package com.duoc.notar_ia.remote.dto.requests

data class SignupRequest(
    val nombre: String,
    val correo: String,
    val contrasena: String
)