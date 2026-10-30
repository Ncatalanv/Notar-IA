package com.duoc.notar_ia.model

// el estado: qué hay escrito y qué error hay
// Estado completo de la pantalla de login
data class LoginUiState(
    val correo: String = "",
    val contrasena: String = "",
    val errorCorreo: String? = null,  // null --> sin error
    val errorContrasena: String? = null,
    val errorGeneral: String? = null,
    val mostrarModal: Boolean = false,
    val loginExitoso: Boolean = false,
    val intentoEnvio: Boolean = false, //Para no mostrar errores mientras la persona escribe
    val mostrarAnimacionExito: Boolean = false,
    val estaCargando: Boolean = false //Mientras se valida el usuario contra Room
){
    // formulario es válido cuando no hay error
    val formularioValido: Boolean
        get() = errorCorreo == null && errorContrasena == null
}