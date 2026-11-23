package com.duoc.notar_ia.remote

import com.duoc.notar_ia.remote.dto.*
import com.duoc.notar_ia.remote.dto.requests.*
import com.duoc.notar_ia.remote.dto.responses.LoginResponse
import com.duoc.notar_ia.remote.dto.responses.SignupResponse
import retrofit2.http.*

// EndPoints que tenemos en xano con su respectiva URL

interface ApiService {

    // ── Auth ──
    @POST("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/signup")
    suspend fun signup(
        @Body request: SignupRequest
    ): SignupResponse

    @POST("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/me")
    suspend fun me(): UsuarioDto

    // ── Trámites ──
    @GET("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/listar_tramites")
    suspend fun listarTramites(): List<TramiteDto>

    @POST("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/crear_tramite")
    suspend fun crearTramite(
        @Body request: CrearTramiteRequest
    ): TramiteDto

    @GET("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/obtener_tramite/{id}")
    suspend fun obtenerTramite(
        @Path("id") id: Int
    ): TramiteDto

    @PATCH("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/actualizar_tramite/{id}")
    suspend fun actualizarTramite(
        @Path("id") id: Int,
        @Body request: ActualizarTramiteRequest
    ): TramiteDto

    @DELETE("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/eliminar_tramite/{id}")
    suspend fun eliminarTramite(
        @Path("id") id: Int
    )

    // ── Documentos ──
    @GET("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/listar_documentos")
    suspend fun listarDocumentos(
        @Query("tramite_id") tramiteId: Int
    ): List<DocumentoDto>

    @POST("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/subir_documento")
    suspend fun subirDocumento(
        @Body request: SubirDocumentoRequest
    ): DocumentoDto

    @DELETE("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/eliminar_documento/{id}")
    suspend fun eliminarDocumento(
        @Path("id") id: Int
    )

    // ── Firma ──
    @GET("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/obtener_firma/{tramite_id}")
    suspend fun obtenerFirma(
        @Path("tramite_id") tramiteId: Int
    ): FirmaDto?

    @POST("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/crear_firma")
    suspend fun crearFirma(
        @Body request: CrearFirmaRequest
    ): FirmaDto

    @PATCH("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/actualizar_firma/{tramite_id}")
    suspend fun actualizarFirma(
        @Path("tramite_id") tramiteId: Int,
        @Body request: ActualizarFirmaRequest
    ): FirmaDto

    // ── Antecedentes ──
    @GET("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/obtener_antecedentes/{tramite_id}")
    suspend fun obtenerAntecedentes(
        @Path("tramite_id") tramiteId: Int
    ): AntecedentesDto?

    @POST("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/crear_antecedentes")
    suspend fun crearAntecedentes(
        @Body request: CrearAntecedentesRequest
    ): AntecedentesDto

    @PATCH("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/actualizar_antecedentes/{tramite_id}")
    suspend fun actualizarAntecedentes(
        @Path("tramite_id") tramiteId: Int,
        @Body request: ActualizarAntecedentesRequest
    ): AntecedentesDto

    // ── Pago ──
    @GET("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/obtener_pago/{tramite_id}")
    suspend fun obtenerPago(
        @Path("tramite_id") tramiteId: Int
    ): PagoDto?

    @POST("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/crear_pago")
    suspend fun crearPago(
        @Body request: CrearPagoRequest
    ): PagoDto

    @PATCH("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/actualizar_pago/{tramite_id}")
    suspend fun actualizarPago(
        @Path("tramite_id") tramiteId: Int,
        @Body request: ActualizarPagoRequest
    ): PagoDto

    // ── Verificación ──
    @GET("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/obtener_verificacion/{tramite_id}")
    suspend fun obtenerVerificacion(
        @Path("tramite_id") tramiteId: Int
    ): VerificacionDto?

    @POST("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/crear_verificacion")
    suspend fun crearVerificacion(
        @Body request: CrearVerificacionRequest
    ): VerificacionDto

    @PATCH("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/actualizar_verificacion/{tramite_id}")
    suspend fun actualizarVerificacion(
        @Path("tramite_id") tramiteId: Int,
        @Body request: ActualizarVerificacionRequest
    ): VerificacionDto

    // ── Catálogo ──
    @GET("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/listar_notarias")
    suspend fun listarNotarias(): List<NotariaDto>

    @GET("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/obtener_notaria/{id}")
    suspend fun obtenerNotaria(
        @Path("id") id: Int
    ): NotariaDto

    // ── Chatbot ──
    @GET("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/listar_mensajes")
    suspend fun listarMensajes(): List<MensajeChatbotDto>

    @POST("https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/enviar_mensaje")
    suspend fun enviarMensaje(
        @Body request: EnviarMensajeRequest
    ): List<MensajeChatbotDto>
}