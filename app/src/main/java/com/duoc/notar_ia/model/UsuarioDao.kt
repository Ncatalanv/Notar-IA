package com.duoc.notar_ia.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Interfaz de acceso a los datos (Data Access Object) --> operaciones que puedo hacer sobre la tabla usuarios
@Dao
interface UsuarioDao{

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertar(usuario: UsuarioEntity): Long

    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    suspend fun buscarPorCorreo(correo: String): UsuarioEntity?

    @Query("SELECT * FROM usuarios LIMIT 1")
    suspend fun obtenerPrimero(): UsuarioEntity?

}