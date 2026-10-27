package com.duoc.notar_ia

import android.app.Application
import com.duoc.notar_ia.model.AppDatabase
import com.duoc.notar_ia.repository.UsuarioRepository
import com.duoc.notar_ia.utils.SessionManager

//crea una sola instancia de la base de datos, repositorio y sesión al iniciar la app

class NotariaApplication: Application(){
    val database by lazy { AppDatabase.getInstance(this) }
    val usuarioRepository by lazy { UsuarioRepository(database.usuarioDao()) }
    val sessionManager by lazy { SessionManager(this) }
}