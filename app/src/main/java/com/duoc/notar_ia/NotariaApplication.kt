package com.duoc.notar_ia

import android.app.Application
import com.duoc.notar_ia.remote.RetrofitInstance
import com.duoc.notar_ia.repository.UsuarioRepository
import com.duoc.notar_ia.utils.SessionManager

// Crea una sola instancia del repositorio, API y sesión al iniciar la app

class NotariaApplication: Application(){
    val sessionManager by lazy { SessionManager(this) }

    override fun onCreate() {
        super.onCreate()
        RetrofitInstance.init(this)
    }

    val usuarioRepository by lazy {
        UsuarioRepository(RetrofitInstance.api, sessionManager)
    }
}