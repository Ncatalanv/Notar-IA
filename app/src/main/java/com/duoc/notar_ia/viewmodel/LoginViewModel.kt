package com.duoc.notar_ia.viewmodel

import androidx.lifecycle.ViewModel
import com.duoc.notar_ia.model.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// lógica: recibe eventos, actualiza el estado

class LoginViewModel : ViewModel(){

    // MutableStateFlow --> por dentro se puede modificar
    //
    private val _uiState = MutableStateFlow(LoginUiState())
    // Estado público de solo lectura. La UI lo observa pero no se modifica
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // función que se activa cuando la persona escribe en el campo de usuario
    fun onUsuarioChange(nuevoUsuario: String){
        _uiState.update {  estadoActual ->
            estadoActual.copy(usuario = nuevoUsuario, errorUsuario = null)
        }
        validar()
    }

    // Se activa cada vez que la persona escribe en el campo de contraseña
    fun onContrasenaChange(nuevaContrasena: String){
        _uiState.update { estadoActual ->
            estadoActual.copy(contrasena = nuevaContrasena)
        }
        validar()
    }

    //Botón "Ingresar" marca el intento, valida, y si está todo bien abre el modal de resultado
    fun onEnviar(){
        _uiState.update { estadoActual -> estadoActual.copy(intentoEnvio = true) }
        validar()

        // Leemos si la validación quedó bien
        val formularioValido = _uiState.value.formularioValido

        // El modal se abre SIEMPRE: con éxito o con error
        _uiState.update { estadoActual ->
            estadoActual.copy(mostrarModal = true, loginExitoso = formularioValido)
        }
    }


    //Botón OK del modal --> lo cierra
    fun onCerrarModal(){
        _uiState.update { estadoActual ->
            estadoActual.copy(mostrarModal = false)
        }
    }

    // Recalcula los mensajes de error según el contenido actual del formulario
    // Lo llamamos después de cada cambio para que los errores estén actualizados
    private fun validar(){
        _uiState.update { estado ->
            estado.copy(
                errorUsuario = when{
                    estado.usuario.isBlank() -> "El usuario es obligatorio"
                    estado.usuario.length < 3 -> "El usuario debe tener al menos 3 caracteres"
                    else -> null
                },
                errorContrasena = when{
                    estado.contrasena.isBlank() -> "La contraseña es obligatoria"
                    estado.contrasena.length < 6 -> "La contraseña debe tener al menos 6 caracteres"
                    else -> null
                }
            )
        }
    }
}