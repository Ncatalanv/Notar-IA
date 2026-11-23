package com.duoc.notar_ia.remote

import android.content.Context
import com.duoc.notar_ia.remote.interceptor.AuthInterceptor
import com.duoc.notar_ia.utils.SessionManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // URL base de Xano
    private const val BASE_URL = "https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/"

    // SessionManager (se inicializa con init())
    private var sessionManager: SessionManager? = null

    // Función para inicializar con el Context de la app
    fun init(context: Context) {
        sessionManager = SessionManager(context)
    }

    // OkHttpClient con el interceptor
    private val client: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY  // muestra requests/responses en Logcat
        }

        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(sessionManager!!))  // interceptor con token
            .addInterceptor(loggingInterceptor)                  // logging para debug
            .build()
    }

    // Retrofit con el cliente que incluye el interceptor
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)  // ← usa el cliente con interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}