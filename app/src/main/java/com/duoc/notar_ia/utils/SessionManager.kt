package com.duoc.notar_ia.utils

import android.content.Context
import android.content.SharedPreferences

// para guardar, recuperar y limpiar la sesión del usuario usando SharedPreferences de Android

class SessionManager(context: Context){

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "notaria_session"
        private const val KEY_SESION_ACTIVA = "sesion_activa"
        private const val KEY_USUARIO_ID = "usuario_id"
        private const val KEY_USUARIO_NOMBRE = "usuario_nombre"
        private const val KEY_USUARIO_CORREO = "usuario_correo"
        private const val KEY_TOKEN = "jwt_token"
    }

    //Guarda los datos de la sesión
    fun saveSession(token: String, id: Int, nombre: String, correo: String) {
        prefs.edit().apply {
            putString(KEY_TOKEN, token)           // guarda el JWT
            putInt(KEY_USUARIO_ID, id)
            putString(KEY_USUARIO_NOMBRE, nombre)
            putString(KEY_USUARIO_CORREO, correo)
            apply()
        }
    }

    //Verificación del token
    fun isLoggedIn(): Boolean = getToken() != null

    fun getUserName(): String{
        return prefs.getString(KEY_USUARIO_NOMBRE, "") ?: ""
    }

    fun getUserEmail(): String{
        return prefs.getString(KEY_USUARIO_CORREO, "") ?: ""
    }

    fun getUserId(): Int{
        return prefs.getInt(KEY_USUARIO_ID, -1)
    }

    fun clearSession(){
        prefs.edit().apply{
            clear()
            apply()
        }
    }

    //Función para obtener el token
    fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

}

