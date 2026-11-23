package com.duoc.notar_ia.remote.interceptor

import com.duoc.notar_ia.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sessionManager: SessionManager) : Interceptor {

    // función que se ejecuta ANTES de cada request HTTP
    override fun intercept(chain: Interceptor.Chain): Response {

        // Obtiene el request original (sin token)
        val originalRequest = chain.request()

        // Lee el token guardado en SessionManager
        val token = sessionManager.getToken()

        // Si hay token, crea un nuevo request con el header Authorization
        val newRequest = if (token != null) {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            // Sin token envía el request tal cual (signup/login)
            originalRequest
        }

        // Continúa con el request (modificado o no)
        return chain.proceed(newRequest)
    }
}