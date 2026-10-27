package com.duoc.notar_ia.model

data class RegistroUiState(
    val nombre: String = "",
    val correo: String = "",
    val contrasena: String = "",
    val confirmarContrasena: String = "",
    val errorNombre: String? = null,
    val errorCorreo: String? = null,
    val errorContrasena: String? = null,
    val errorConfirmarContrasena: String? = null,
    val errorGeneral: String? = null,
    val mostrarModal: Boolean = false,
    val registroExitoso: Boolean = false,
    val intentoEnvio: Boolean = false
){
    val formularioValido: Boolean
        get() = errorNombre == null && errorCorreo == null &&
                errorContrasena == null && errorConfirmarContrasena == null

}