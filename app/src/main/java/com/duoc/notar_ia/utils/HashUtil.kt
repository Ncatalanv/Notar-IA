package com.duoc.notar_ia.utils

import java.security.MessageDigest

// Convierte la contraseña en un hash SHA-256
object HashUtil {
    fun hash(contrasena: String, salt: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = (salt + contrasena).toByteArray(Charsets.UTF_8)
        val hashed = digest.digest(bytes)
        return hashed.joinToString("") { "%02x".format(it) }
    }
}
