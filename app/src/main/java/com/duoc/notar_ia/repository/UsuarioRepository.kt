package com.duoc.notar_ia.repository

import android.database.sqlite.SQLiteConstraintException
import com.duoc.notar_ia.model.UsuarioDao
import com.duoc.notar_ia.model.UsuarioEntity
import com.duoc.notar_ia.utils.HashUtil

// Conecta todos los ViewModels con ROOM. Lógica de registro y login
import com.duoc.notar_ia.remote.ApiService
import com.duoc.notar_ia.remote.dto.UsuarioDto
import com.duoc.notar_ia.remote.dto.responses.LoginResponse
import com.duoc.notar_ia.remote.dto.requests.SignupRequest
import com.duoc.notar_ia.remote.dto.requests.LoginRequest
import com.duoc.notar_ia.utils.SessionManager

class UsuarioRepository(
    private val api: ApiService,
    private val sessionManager: SessionManager
) {

    suspend fun registrar(nombre: String, correo: String, contrasena: String): Result<Unit> {
        return try {
            val response = api.signup(SignupRequest(nombre, correo, contrasena))
            if (response.success) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(correo: String, contrasena: String): Result<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(correo, contrasena))
            if (response.success && response.token != null) {
                // Guarda el token + datos del usuario
                sessionManager.saveSession(
                    token = response.token,
                    id = response.usuario!!.id,
                    nombre = response.usuario.nombre,
                    correo = response.usuario.correo
                )
                Result.success(response)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // este endpoint funciona porque el interceptor agrega el token automáticamente
    suspend fun obtenerUsuarioActual(): Result<UsuarioDto> {
        return try {
            val usuario = api.me()  // ← el interceptor agrega "Authorization: Bearer <token>"
            Result.success(usuario)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
