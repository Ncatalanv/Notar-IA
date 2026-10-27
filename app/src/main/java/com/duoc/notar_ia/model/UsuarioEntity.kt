package com.duoc.notar_ia.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//Tabla SQLite que Room creará automáticamente

@Entity(
    tableName = "usuarios",
    indices = [Index(value = ["correo"], unique = true)]
)
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val correo: String,
    val contrasenaHashed: String
)