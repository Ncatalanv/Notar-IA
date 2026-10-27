package com.duoc.notar_ia.utils

import java.security.MessageDigest

//Convierte la contraseña en un hash SHA-256
object HashUtil {                                                                                                                     Context                              █
    fun hash(contrasena: String, salt: String): String {                                                                              28.077 tokens                        █
        val digest = MessageDigest.getInstance("SHA-256")                                                                             11% used                             █
        val bytes = (salt + contrasena).toByteArray(Charsets.UTF_8)                                                                   $0.09 spent                          █
        val hashed = digest.digest(bytes)                                                                                                                                  █
        return hashed.joinToString("") { "%02x".format(it) }                                                                          MCP                                  █
    }                                                                                                                                 • Sanity Needs auth                  █
}