package com.duoc.notar_ia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.duoc.notar_ia.repository.UsuarioRepository
import com.duoc.notar_ia.utils.SessionManager


//Los ViewModels no pueden recibir parámetros al crearlos con viewModel() por defecto. Esta factory le dice a Compose/Android cómo crear un LoginViewModel inyectándole el UsuarioRepository y SessionManager.
class LoginViewModelFactory (
    private val usuarioRepository: UsuarioRepository,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(usuarioRepository, sessionManager) as T
        }
        throw IllegalArgumentException("ViewModel desconocido! ${modelClass.name} ")
    }
}