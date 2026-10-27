package com.duoc.notar_ia.repository

import android.database.sqlite.SQLiteConstraintException
import com.duoc.notar_ia.model.UsuarioDao
import com.duoc.notar_ia.model.UsuarioEntity
import com.duoc.notar_ia.utils.HashUtil

// Conecta todos los ViewModels con ROOM. Lógica de registro y login
class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun registrar(nombre: String, correo: String, contrasena: String): Result<Unit> {
        return try {
            val hashed = HashUtil.hash(contrasena, salt = correo)
            val usuario = UsuarioEntity(
                nombre = nombre,
                correo = correo,
                contrasenaHashed = hashed
            )
            usuarioDao.insertar(usuario)
            Result.success(Unit)
        } catch (e: SQLiteConstraintException) {
            Result.failure(Exception("El correo ya está registrado"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(correo: String, contrasena: String): UsuarioEntity? {
        val usuario = usuarioDao.buscarPorCorreo(correo) ?: return null
        val hashed = HashUtil.hash(contrasena, salt = correo)
        return if (usuario.contrasenaHashed == hashed) usuario else null
    }
}
