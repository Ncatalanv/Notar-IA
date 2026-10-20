# ✅ Tareas — Notar-IA

> Proyecto: App de documentos notariales (Duoc)
> Integrantes: [Nombre A] · [Nombre B]
> Metodología: MVVM · Jetpack Compose · Room · Git + Trello

**Leyenda:** `[ ]` pendiente · `[x]` hecho · (A) Persona A · (B) Persona B · (AB) ambos

---

## 🏁 Sprint 1 — Entregable 1 (básico)

### 1. Estructura de carpetas MVVM + dependencias (B)
- [ ] Crear paquetes: `data/local`, `data/repository`, `viewmodel`, `navigation`, `ui/screens`, `ui/components`
- [ ] Agregar Room al `libs.versions.toml` y `build.gradle.kts`
- [ ] Agregar Navigation Compose
- [ ] Agregar ViewModel (`lifecycle-viewmodel-compose`)
- [ ] Eliminar `dfdf.txt`
- [ ] El proyecto compila sin errores

### 2. Pantalla Login con diseño base (A)
- [ ] Mover `Login.kt` → `ui/screens/LoginScreen.kt`
- [ ] Logo, título y eslogan
- [ ] Paleta de colores formal en `Color.kt` / `Theme.kt`
- [ ] Campo contraseña con teclado tipo password e íconos

### 3. Validación de campos del formulario (A)
- [ ] Usuario no vacío (o formato email si se decide usar correo)
- [ ] Contraseña mínimo 6–8 caracteres
- [ ] Mensajes de error bajo cada campo (`isError` + `supportingText`)
- [ ] Botón deshabilitado hasta que el formulario sea válido

### 4. Migrar Login a MVVM — LoginViewModel (B)
- [ ] Crear `LoginViewModel` con `StateFlow` (usuario, contraseña, errores)
- [ ] La UI solo observa el estado (sin `remember` para datos de negocio)
- [ ] Nombres de variables descriptivos + comentarios en el código

### 5. Modal de resultado (A)
- [ ] AlertDialog muestra éxito o error según validación
- [ ] Botón OK cierra el modal

---

## 🚀 Sprint 2 — Completar la rúbrica

### 6. Persistencia con Room (registro y login) (B)
- [ ] `UsuarioEntity`
- [ ] `UsuarioDao`
- [ ] `AppDatabase`
- [ ] `UsuarioRepository`
- [ ] Guardar/validar usuario en Room al iniciar sesión
- [ ] Verificar persistencia (Database Inspector o log)

### 7. Pantalla Home + Navegación (B)
- [ ] Crear `HomeScreen` con bienvenida al usuario (nombre desde Room)
- [ ] `NavHost` con rutas `login` → `home`
- [ ] Navegar solo si el login es válido

### 8. Animación (A)
- [ ] Fade-in del logo / `AnimatedVisibility` al entrar, o animación del botón al enviar
- [ ] Visible y notoria para el video

### 9. Recurso nativo (AB)
- [ ] Elegir: 📷 cámara (escanear documento) o 🔔 notificación
- [ ] Permiso en `AndroidManifest.xml`
- [ ] Solicitud de permiso en runtime
- [ ] Funciona en dispositivo físico o emulador

### 10. README.md (A)
- [ ] Nombre del proyecto, integrantes, descripción
- [ ] Tecnologías usadas
- [ ] Cómo ejecutar el proyecto
- [ ] Capturas de pantalla

### 11. Video de funcionamiento (AB)
- [ ] 1–3 min mostrando: validación, modal, animación, navegación, Room, recurso nativo
- [ ] Enviado al cliente/profesor

### 12. Revisión final contra rúbrica (AB)
- [ ] Formulario ✅
- [ ] Validación ✅
- [ ] Imagen ✅
- [ ] Animación ✅
- [ ] Modal ✅
- [ ] Room ✅
- [ ] Navegación ✅
- [ ] Recurso nativo ✅
- [ ] Git con commits descriptivos de ambos ✅
- [ ] Trello con todas las tarjetas en "Hecho" ✅
- [ ] Video ✅

---

## 🏆 Puntos extra (transversal, todo el proyecto)

- [ ] Paleta de colores apropiada y diseño bonito
- [ ] Comentarios descriptivos en commits y código
- [ ] Variables con nombres descriptivos
- [ ] README completo en el repositorio
- [ ] Tarjetas del Trello siempre actualizadas
