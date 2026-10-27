package com.duoc.notar_ia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.notar_ia.model.RegistroUiState
import com.duoc.notar_ia.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistroViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistroUiState())
    val uiState: StateFlow<RegistroUiState> = _uiState.asStateFlow()

    fun onNombreChange(valor: String) {
        _uiState.update { it.copy(nombre = valor, errorNombre = null, errorGeneral = null) }
        validar()
    }

    fun onCorreoChange(valor: String) {
        _uiState.update { it.copy(correo = valor, errorCorreo = null, errorGeneral = null) }
        validar()
    }

    fun onContrasenaChange(valor: String) {
        _uiState.update { it.copy(contrasena = valor, errorContrasena = null, errorGeneral = null) }
        validar()
    }

    fun onConfirmarChange(valor: String) {
        _uiState.update { it.copy(confirmarContrasena = valor, errorConfirmarContrasena = null, errorGeneral = null) }
        validar()
    }

    fun onRegistrar() {
        _uiState.update { it.copy(intentoEnvio = true, errorGeneral = null) }
        validar()

        if (!_uiState.value.formularioValido) {
            _uiState.update { it.copy(mostrarModal = true, registroExitoso = false) }
            return
        }

        viewModelScope.launch {
            val resultado = usuarioRepository.registrar(
                nombre = _uiState.value.nombre,
                correo = _uiState.value.correo,
                contrasena = _uiState.value.contrasena
            )
            resultado.fold(
                onSuccess = {
                    _uiState.update { it.copy(mostrarModal = true, registroExitoso = true) }
                },
                onFailure = {
                    _uiState.update {
                        it.copy(
                            mostrarModal = true,
                            registroExitoso = false,
                            errorGeneral = "El correo ya está registrado"
                        )
                    }
                }
            )
        }
    }

    fun onCerrarModal() {
        _uiState.update { it.copy(mostrarModal = false) }
    }

    private fun validar() {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        _uiState.update { estado ->
            estado.copy(
                errorNombre = when {
                    estado.nombre.isBlank() -> "El nombre es obligatorio"
                    estado.nombre.length < 3 -> "El usuario debe tener al menos 3 caracteres"
                    else -> null
                },
                errorCorreo = when {
                    estado.correo.isBlank() -> "El correo es obligatorio"
                    estado.correo.length < 7 -> "El correo debe tener al menos 7 caracteres"
                    !emailRegex.matches(estado.correo) -> "Ingresa un correo válido"
                    else -> null
                },
                errorContrasena = when {
                    estado.contrasena.isBlank() -> "La contraseña es obligatoria"
                    estado.contrasena.length < 6 -> "La contraseña debe tener al menos 6 caracteres"
                    else -> null
                },
                errorConfirmarContrasena = when {
                    estado.confirmarContrasena.isBlank() -> "Debes confirmar la contraseña"
                    estado.confirmarContrasena != estado.contrasena -> "Las contraseñas no coinciden"
                    else -> null
                }
            )
        }
    }
}
