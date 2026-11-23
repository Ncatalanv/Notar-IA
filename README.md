# Notar-IA

Aplicación móvil Android que guía al usuario durante la creación de trámites notariales: registro e inicio de sesión, asistente de trámite, carga de documentos, verificación facial, selección de notarías, seguimiento de trámites y chatbot de consultas.

El backend es **Xano** (Base de Datos + API REST) y la app consume sus endpoints mediante **Retrofit** con autenticación **JWT**.

- Repositorio: https://github.com/Ncatalanv/Notar-IA.git
- Paquete Android: `com.duoc.notar_ia`
- Versión: `1.0` (`versionCode = 1`)

---

## Tecnologías utilizadas

| Capa | Tecnología |
|---|---|
| Lenguaje | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Navegación | Navigation Compose (`NavHost`) |
| Arquitectura | MVVM (ViewModel + Repository + DTOs) |
| Red | Retrofit 2.9.0 + Gson + OkHttp 4.12.0 + Logging Interceptor |
| Asincronía | Kotlin Coroutines (`kotlinx-coroutines-android:1.7.3`) |
| Persistencia local | SharedPreferences (sesión/JWT) y Room (estructura local heredada) |
| Backend | Xano (PostgreSQL + API REST) |
| Build | Android Gradle Plugin + Kotlin DSL (`build.gradle.kts`) |
| Inyección manual de dependencias | `NotariaApplication` (.Application singleton) |

SDK configurado en `app/build.gradle.kts`: `minSdk = 24`, `targetSdk = 37`, `compileSdk = 37`.

---

## Requisitos previos

- Android Studio (con SDK de Android instalado).
- JDK 11 (configurado en `compileOptions`).
- Dispositivo o emulador Android con API 24 o superior.
- Acceso a la instancia de Xano (los endpoints deben estar **publicados/Live**, no en Draft).

---

## Ejecución del proyecto

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Ncatalanv/Notar-IA.git
   cd Notar-IA
   ```
2. Abrir la carpeta en Android Studio y esperar la sincronización de Gradle.
3. Ejecutar en un emulador o dispositivo físico (botón **Run**).
4. En la primera pantalla, registrar un usuario con **Registro** o iniciar sesión con **Login**.

Comandos útiles:

```bash
.\gradlew.bat compileDebugKotlin --no-daemon   # compilar Kotlin
.\gradlew.bat assembleDebug --no-daemon        # generar APK de debug
.\gradlew.bat assembleRelease --no-daemon      # generar APK de release (requiere firma configurada)
```

> La APK de **release firmada** se genera desde Android Studio: **Build → Generate Signed Bundle / APK → APK → release**, usando un keystore propio. El archivo `.jks` y su contraseña **no se suben al repositorio**.

---

## Arquitectura y estructura del código

```
app/src/main/
├── AndroidManifest.xml                     # Permisos y actividad principal
├── java/com/duoc/notar_ia/
│   ├── MainActivity.kt                     # Actividad única (Jetpack Compose)
│   ├── NotariaApplication.kt               # Inicializa Retrofit, SessionManager y Repository
│   ├── remote/
│   │   ├── ApiService.kt                   # 27 endpoints de Xano (Retrofit)
│   │   ├── RetrofitInstance.kt             # BASE_URL, OkHttp, interceptor y logging
│   │   ├── interceptor/AuthInterceptor.kt # Agrega "Authorization: Bearer <JWT>"
│   │   ├── dto/                            # Modelos de respuesta (UsuarioDto, TramiteDto, ...)
│   │   ├── dto/requests/                   # DTOs de petición (SignupRequest, ...)
│   │   └── dto/responses/                  # LoginResponse, SignupResponse
│   ├── repository/UsuarioRepository.kt     # Registro, login y usuario actual contra Xano
│   ├── utils/
│   │   ├── SessionManager.kt               # JWT + datos de sesión (SharedPreferences)
│   │   └── HashUtil.kt                     # Utilidad de hash local (estructura heredada)
│   ├── model/                              # Entidades/DAO Room y estados de UI
│   ├── viewmodel/                          # LoginViewModel, RegistroViewModel + factories
│   └── ui/
│       ├── navigation/AppNavigation.kt     # Rutas y grafo de navegación
│       ├── screens/                        # Pantallas Compose
│       └── theme/                          # Colores, tipografía y tema (dorado/oscuro)
└── res/
    ├── drawable/logo_notar_ia.png          # Logotipo de la app
    └── mipmap/                             # Íconos de launcher (@mipmap/ic_launcher)
```

### Flujo de autenticación

1. `LoginScreen` / `RegistroScreen` → `LoginViewModel` / `RegistroViewModel` → `UsuarioRepository`.
2. `UsuarioRepository` llama a `POST /signup` o `POST /login` en Xano.
3. Si el login es exitoso, se guarda el **JWT** y los datos del usuario en `SharedPreferences` mediante `SessionManager.saveSession(...)`.
4. `AuthInterceptor` agrega automáticamente el header `Authorization: Bearer <token>` a todas las peticiones.
5. `AppNavigation` decide el destino inicial: `HOME` si hay token, `LOGIN` si no.
6. El cierre de sesión limpia la sesión con `SessionManager.clearSession()`.

**Configuración de la API** (`remote/RetrofitInstance.kt`):

```kotlin
private const val BASE_URL = "https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E/"
```

- La `BASE_URL` **debe terminar con `/`** (requerimiento de Retrofit).
- Las rutas declaradas en `ApiService.kt` son URLs absolutas y **no deben terminar con `/`**; si el endpoint se llama con barra final, Xano responde `404 ERROR_CODE_NOT_FOUND`.
- `signup` y `login` son públicos; el resto de endpoints requieren el JWT.

---

## Endpoints consumidos (Xano)

Base: `https://x8ki-letl-twmt.n7.xano.io/api:GePo2t_E`

| # | Método | Endpoint | Descripción |
|---|---|---|---|
| 1 | POST | `/signup` | Registrar usuario |
| 2 | POST | `/login` | Iniciar sesión y obtener JWT |
| 3 | GET | `/me` | Obtener usuario autenticado |
| 4 | GET | `/listar_tramites` | Listar trámites |
| 5 | POST | `/crear_tramite` | Crear trámite |
| 6 | GET | `/obtener_tramite/{id}` | Obtener trámite por ID |
| 7 | PATCH | `/actualizar_tramite/{id}` | Actualizar trámite |
| 8 | DELETE | `/eliminar_tramite/{id}` | Eliminar trámite |
| 9 | GET | `/listar_documentos` | Listar documentos de un trámite |
| 10 | POST | `/subir_documento` | Subir documento |
| 11 | DELETE | `/eliminar_documento/{id}` | Eliminar documento |
| 12 | GET | `/obtener_firma/{tramite_id}` | Obtener firma de un trámite |
| 13 | POST | `/crear_firma` | Crear firma |
| 14 | PATCH | `/actualizar_firma/{tramite_id}` | Actualizar firma |
| 15 | GET | `/obtener_antecedentes/{tramite_id}` | Obtener antecedentes legales |
| 16 | POST | `/crear_antecedentes` | Crear antecedentes legales |
| 17 | PATCH | `/actualizar_antecedentes/{tramite_id}` | Actualizar antecedentes |
| 18 | GET | `/obtener_pago/{tramite_id}` | Obtener pago |
| 19 | POST | `/crear_pago` | Crear pago |
| 20 | PATCH | `/actualizar_pago/{tramite_id}` | Actualizar pago |
| 21 | GET | `/obtener_verificacion/{tramite_id}` | Obtener verificación de identidad |
| 22 | POST | `/crear_verificacion` | Crear verificación |
| 23 | PATCH | `/actualizar_verificacion/{tramite_id}` | Actualizar verificación |
| 24 | GET | `/listar_notarias` | Listar notarías disponibles |
| 25 | GET | `/obtener_notaria/{id}` | Obtener notaría por ID |
| 26 | GET | `/listar_mensajes` | Listar mensajes del chatbot |
| 27 | POST | `/enviar_mensaje` | Enviar mensaje al chatbot |

> Cumple el requisito de consumo de APIs externas: **27 endpoints distintos**, con al menos un **GET** y un **POST**.

---

## Pantallas y navegación

Las rutas están definidas en `ui/navigation/AppNavigation.kt` (`object Routes`):

| Ruta | Pantalla | Funcionalidad |
|---|---|---|
| `login` | `LoginScreen` | Inicio de sesión |
| `registro` | `RegistroScreen` | Crear cuenta |
| `home` | `HomeScreen` | Menú principal + bottom navigation |
| `perfil` | `PerfilScreen` | Datos del usuario y cerrar sesión |
| `crear_tramite` | `CrearTramiteAsistenteScreen` | Asistente de creación de trámite |
| `cargar_documento` | `CargarDocumentoScreen` | Adjuntar PDF/imagen del dispositivo |
| `tipo_firma` | `TipoFirmaMetodoScreen` | Selección del tipo de firma |
| `antecedentes_legales` | `AntecedentesLegalesScreen` | Declaración de antecedentes |
| `pago_identidad` | `PagoIdentidadScreen` | Pago y verificación facial |
| `face_capture` | `FaceCaptureScreen` | Captura de rostro con cámara |
| `notarias_disponibles` | `NotariasDisponiblesScreen` | Catálogo de notarías |
| `detalle_notaria/{nombre}` | `DetalleNotariaScreen` | Detalle de la notaría elegida |
| `mis_tramites` | `MisTramitesScreen` | Seguimiento de trámites |
| `chatbot` | `ChatbotConversacionScreen` | Asistente virtual |

---

## Recursos nativos implementados

### 1. Cámara (`FaceCaptureScreen.kt`)
- Permiso: `android.permission.CAMERA` (solicitado en runtime).
- Uso: captura facial para la verificación de identidad del trámite.
- Manejo de permiso denegado y retorno del resultado a `PagoIdentidadScreen` mediante `SavedStateHandle`.

### 2. Selector de archivos (`CargarDocumentoScreen.kt`)
- Permisos en el manifest: `READ_EXTERNAL_STORAGE` (hasta API 32), `READ_MEDIA_IMAGES` y `READ_MEDIA_DOCUMENTS`.
- Uso: `rememberLauncherForActivityResult(ActivityResultContracts.GetContent())` con `launch("*/*")`.
- Obtiene el nombre real del archivo con `ContentResolver` + `OpenableColumns.DISPLAY_NAME`.
- La tarjeta y un botón permiten abrir el explorador del sistema; el campo manual sigue disponible como alternativa.

### Permisos declarados en `AndroidManifest.xml`

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" android:maxSdkVersion="32" />
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
<uses-permission android:name="android.permission.READ_MEDIA_DOCUMENTS" />
```

> El selector usa el *Storage Access Framework*, por lo que no requiere permisos en tiempo de ejecución: el sistema entrega acceso temporal a la URI seleccionada.

---

## Ícono personalizado

- Logotipo: `app/src/main/res/drawable/logo_notar_ia.png`.
- El launcher usa `android:icon="@mipmap/ic_launcher"` y `android:roundIcon="@mipmap/ic_launcher_round"` (definidos en `res/mipmap-*`).
- Para regenerar los íconos: **app → New → Image Asset** y seleccionar `logo_notar_ia.png`.

---

## Pruebas

Las dependencias para pruebas ya están configuradas en `build.gradle.kts` (`junit`, `androidx.junit`, Espresso y Compose Test).

Se implementaron **4 pruebas unitarias** en `app/src/test/java/com/duoc/notar_ia/NotariaUnitTest.kt`:

1. `hashContrasenaEsConsistente()` — el hash de `HashUtil` es determinista con la misma sal.
2. `hashCambiaCuandoCambiaLaSal()` — sales distintas producen hashes distintos.
3. `formularioLoginValidoSinErrores()` — `LoginUiState.formularioValido` es `true` sin errores.
4. `formularioLoginInvalidoConError()` — `formularioValido` es `false` cuando hay error de correo.

Para ejecutarlas:

```bash
.\gradlew.bat testDebugUnitTest --no-daemon
```

---

## Generación del APK firmado (release)

1. **Build → Generate Signed Bundle / APK**.
2. Seleccionar **APK** y luego **Create new...** para generar un keystore (o usar uno existente).
3. Completar alias, contraseñas y validez (recomendado 25 años).
4. Seleccionar el build variant **release**.
5. El resultado se guarda en `app/release/app-release.apk`.
6. Guardar el `.jks` y sus contraseñas fuera del repositorio (sin versionar).

---

## Estado frente a la rúbrica

| Requisito | Estado | Detalle |
|---|---|---|
| Consumo de APIs externas (mín. 7, ≥1 GET y ≥1 POST) | Sí | 27 endpoints en Xano |
| 2 recursos nativos (cámara, GPS, archivos o notificaciones) | Sí | Cámara + selector de archivos |
| Pruebas unitarias (al menos 2) | Sí | 4 pruebas en `NotariaUnitTest.kt` |
| Generación de APK firmado (release) | No | Falta generar el APK firmado |
| Repositorio GIT actualizado | Sí | Commit y push realizados |
| Tablero Trello actualizado | Pendiente | Falta marcar tarjetas |
| Documentación técnica en README | Sí | Este documento |
| APK con ícono personalizado | Parcial | Ícono corregido; falta el APK release |
