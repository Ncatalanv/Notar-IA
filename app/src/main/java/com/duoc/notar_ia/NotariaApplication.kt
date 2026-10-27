package com.duoc.notar_ia

import android.app.Application
import com.duoc.notar_ia.model.AppDatabase
import com.duoc.notar_ia.repository.UsuarioRepository

//crea una sola instancia de la base de datos y del repositorio al iniciar la app

class NotariaApplication: Application(){
    val database by lazy { AppDatabase.getInstance(this) }
    val usuarioRepository by lazy { UsuarioRepository(database.usuarioDao()) }
}