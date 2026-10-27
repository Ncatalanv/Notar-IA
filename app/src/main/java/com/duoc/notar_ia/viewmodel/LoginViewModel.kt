package com.duoc.notar_ia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.notar_ia.model.LoginUiState
import com.duoc.notar_ia.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// lógica: recibe eventos, actualiza el estado y valida contra Room

class LoginViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    // MutableStateFlow --> por dentro se puede modificar
    private val _uiState = MutableStateFlow(LoginUiState())
    // Estado público de solo lectura. La UI lo observa pero no se modifica
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // función que se activa cuando la persona escribe en el campo de correo
    fun onCorreoChange(nuevoCorreo: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(correo = nuevoCorreo, errorCorreo = null, errorGeneral = null)
        }
        validar()
    }

    // Se activa cada vez que la persona escribe en el campo de contraseña
    fun onContrasenaChange(nuevaContrasena: String) {
        _uiState.update { estadoActual ->
            estadoActual.copy(contrasena = nuevaContrasena, errorContrasena = null, errorGeneral = null)
        }
        validar()
    }

    // Botón "Ingresar" marca el intento, valida, y si está todo bien consulta Room
    fun onEnviar() {
        _uiState.update { estadoActual ->
            estadoActual.copy(intentoEnvio = true, errorGeneral = null)
        }
        validar()

        if (!_uiState.value.formularioValido) {
            _uiState.update { estadoActual ->
                estadoActual.copy(mostrarModal = true, loginExitoso = false)
            }
            return
        }

        viewModelScope.launch {
            val usuario = usuarioRepository.login(
                correo = _uiState.value.correo,
                contrasena = _uiState.value.contrasena
            )
            if (usuario != null) {
                _uiState.update { estadoActual ->
                    estadoActual.copy(
                        mostrarModal = true,
                        loginExitoso = true
                    )
                }
            } else {
                _uiState.update { estadoActual ->
                    estadoActual.copy(
                        mostrarModal = true,
                        loginExitoso = false,
                        errorGeneral = "Usuario o contraseña incorrectos"
                    )
                }
            }
        }
    }

    // Botón OK del modal --> lo cierra
    fun onCerrarModal() {
        _uiState.update { estadoActual ->
            estadoActual.copy(mostrarModal = false)
        }
    }

    // Recalcula los mensajes de error según el contenido actual del formulario
    private fun validar() {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        _uiState.update { estado ->
            estado.copy(
                errorCorreo = when {
                    estado.correo.isBlank() -> "El correo es obligatorio"
                    !emailRegex.matches(estado.correo) -> "Ingresa un correo válido"
                    else -> null
                },
                errorContrasena = when {
                    estado.contrasena.isBlank() -> "La contraseña es obligatoria"
                    estado.contrasena.length < 6 -> "La contraseña debe tener al menos 6 caracteres"
                    else -> null
                }
            )
        }
    }
}
