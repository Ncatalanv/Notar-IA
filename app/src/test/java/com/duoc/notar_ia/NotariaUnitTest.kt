package com.duoc.notar_ia

import com.duoc.notar_ia.model.LoginUiState
import com.duoc.notar_ia.utils.HashUtil
import org.junit.Assert.*
import org.junit.Test

class NotarIaUnitTest {

    // PRUEBA 1: El hash de una contraseña es consistente
    @Test
    fun hashContrasenaEsConsistente() {
        val hash1 = HashUtil.hash("123456", "sal123")
        val hash2 = HashUtil.hash("123456", "sal123")

        assertEquals(hash1, hash2)
        assertNotEquals("123456", hash1)
    }

    // PRUEBA 2: Una sal diferente produce otro hash
    @Test
    fun hashCambiaCuandoCambiaLaSal() {
        val hash1 = HashUtil.hash("123456", "sal123")
        val hash2 = HashUtil.hash("123456", "sal456")

        assertNotEquals(hash1, hash2)
    }

    // PRUEBA 3: Validación del formulario de inicio de sesión
    @Test
    fun formularioLoginValidoSinErrores() {
        val estado = LoginUiState(
            correo = "usuario@gmail.com",
            contrasena = "123456"
        )

        assertTrue(estado.formularioValido)
    }

    // PRUEBA 4: El formulario no es válido si existe un error
    @Test
    fun formularioLoginInvalidoConError() {
        val estado = LoginUiState(
            correo = "correoIncorrecto",
            contrasena = "123456",
            errorCorreo = "Ingresa un correo válido"
        )

        assertFalse(estado.formularioValido)
    }
}
