# 📋 Tablero de Trello — Notar-IA (Entregable 1)

> **Proyecto:** DSY1105 · Duoc UC

**Cómo pegar en Trello:** cada checklist se puede pegar completo — si pegas varias líneas en una checklist de Trello, cada línea se convierte en un ítem. *(Copia desde la vista renderizada de GitHub para que no se pegue el `- [ ]`)*

**Leyenda de responsables:** (A) Persona A · (B) Persona B · (AB) ambos
**Etiquetas:** 🎨 Diseño · ⚙️ Funcional · 🤝 Colaboración · 📄 Documentación
**Fechas límite:** por definir cuando se confirme la fecha de entrega.

---

## ✅ Lista: Hecho

### Tema oscuro con paleta corporativa `🎨 Diseño`
- [x] Dorado `#D4AF6A` y fondo `#0B0B0D` en `Color.kt`
- [x] Esquema de colores propio en `Theme.kt`
- [x] Tema oscuro forzado, sin colores dinámicos

### Logo de la app `🎨 Diseño`
- [x] Imagen `logo_notar_ia.png` mostrada en el login

### Login base `⚙️ Funcional`
- [x] Campos de nombre y contraseña
- [x] Botón Enviar
- [x] Contenido centrado sobre el fondo oscuro

### Primera versión del modal `⚙️ Funcional`
- [x] AlertDialog al enviar el formulario

### Proyecto subido a GitHub `🤝 Colaboración`
- [x] Repositorio creado y primeros commits

---

## 🔨 Lista: En progreso

### Pulir el diseño del Login (A) `🎨 Diseño`
- [ ] Frase pegada al logo y separada del formulario
- [ ] Estilos de campos, botón y diálogo con el tema
- [ ] Usar `GrisMedio` en campos y superficies secundarias
- [ ] Campo de contraseña con teclado tipo password e íconos

---

## 🏁 Lista: Sprint 1

### 1. Estructura MVVM y dependencias (B) `🤝 Colaboración`
- [ ] Paquetes: `data/local`, `data/repository`, `viewmodel`, `navigation`, `ui/screens`, `ui/components`
- [ ] Un archivo en cada paquete, para que Git los guarde
- [ ] Agregar Room, Navigation Compose y `lifecycle-viewmodel-compose`
- [ ] Eliminar `dfdf.txt`
- [ ] El proyecto compila sin errores

### 2. Acordar el flujo de Git (AB) `🤝 Colaboración`
- [ ] Invitar al colega al repositorio y al Trello
- [ ] Definir ramas: `develop` y una por función
- [ ] Acordar el formato de los commits (mensajes descriptivos)

### 3. Mover el Login a ui/screens (A) `⚙️ Funcional`
- [ ] `Login.kt` → `ui/screens/LoginScreen.kt`
- [ ] Hacerlo después de la tarjeta 1

### 4. LoginViewModel con StateFlow (B) `⚙️ Funcional`
- [ ] Estado con usuario, contraseña y errores
- [ ] La UI solo observa el estado
- [ ] Nombres descriptivos y comentarios en el código

### 5. Validación de campos (A) `⚙️ Funcional`
- [ ] Hacerla después de la tarjeta 4, dentro del ViewModel
- [ ] Usuario no vacío (o formato de correo, si se decide)
- [ ] Contraseña de 6 a 8 caracteres como mínimo
- [ ] Mensajes de error bajo cada campo (`isError` y `supportingText`)
- [ ] Botón deshabilitado hasta que el formulario sea válido

### 6. Modal de resultado (A) `⚙️ Funcional`
- [ ] Quitar la contraseña del contenido
- [ ] Mostrar éxito o error según la validación
- [ ] El botón OK cierra el modal

---

## 🚀 Lista: Sprint 2

### 7. Room: base de datos de usuarios (B) `⚙️ Funcional`
- [ ] `UsuarioEntity`, `UsuarioDao` y `AppDatabase`
- [ ] `UsuarioRepository`
- [ ] No guardar la contraseña en texto plano (hash simple)

### 8. Pantalla de registro (A) `⚙️ Funcional`
- [ ] Formulario con la misma validación del login
- [ ] Guardar un usuario ficticio en Room

### 9. Login validado contra Room (B) `⚙️ Funcional`
- [ ] Buscar el usuario y comparar la contraseña
- [ ] Mensaje de error si no existe
- [ ] Verificar la persistencia (Database Inspector o log)

### 10. Home y navegación (B) `⚙️ Funcional`
- [ ] `HomeScreen` con bienvenida usando el nombre desde Room
- [ ] `NavHost` con rutas login, registro y home
- [ ] Navegar solo si el login es válido
- [ ] Botón para cerrar sesión

### 11. Animación (A) `⚙️ Funcional`
- [ ] Fade-in del logo, o animación del botón al enviar
- [ ] Que se note bien en el video

### 12. Recurso nativo: cámara (AB) `⚙️ Funcional`
- [ ] Permiso en `AndroidManifest.xml`
- [ ] Solicitud del permiso en tiempo de ejecución
- [ ] Tomar una foto de un documento ficticio
- [ ] Mostrar la imagen en pantalla
- [ ] Manejar el permiso denegado

### 13. Pruebas manuales (AB) `⚙️ Funcional`
- [ ] Campos vacíos y contraseña corta
- [ ] Usuario inexistente
- [ ] Datos que persisten al cerrar y abrir la app
- [ ] Permiso de cámara denegado

### 14. README (A) `📄 Documentación`
- [ ] Nombre, integrantes y descripción
- [ ] Tecnologías usadas
- [ ] Cómo ejecutar el proyecto
- [ ] Capturas de pantalla
- [ ] Próximos pasos, con los mockups como visión de la app

### 15. Video de funcionamiento (AB) `📄 Documentación`
- [ ] Guion: validación, modal, animación, navegación, Room y cámara
- [ ] Grabar de 1 a 3 minutos
- [ ] Enviarlo al cliente o profesor

### 16. Revisión final contra la rúbrica (AB) `🤝 Colaboración`
- [ ] Formulario, validación, imagen, animación y modal
- [ ] Room, navegación y recurso nativo
- [ ] Commits descriptivos de ambos
- [ ] Trello ordenado
- [ ] Video enviado

---

## 📌 Tarjeta fija: Buenas prácticas

> ⚠️ Déjala abierta en la lista **Sprint 2** hasta el final.

- [ ] Commits descriptivos de ambos
- [ ] Comentarios útiles en el código
- [ ] Nombres de variables descriptivos
- [ ] Trello siempre actualizado
- [ ] Paleta de colores coherente en todas las pantallas

---

## 📥 Lista: Backlog *(después del entregable)*

> Una tarjeta por cada una, sin checklist todavía:

- 💡 Asistente que sugiere el trámite adecuado
- 💬 Chatbot de preguntas frecuentes basado en reglas
- 📄 Escaneo del documento con OCR
- 📍 Pantalla de notarías disponibles
- 📝 Formulario de trámite y seguimiento de estados
- 🧑‍⚖️ Rol de notario: bandeja y revisión
- ✍️ Firma avanzada, pago e identidad simulados, videollamada simulada

---

## 🔗 Orden de dependencias

Para que no se bloqueen entre ustedes:

1. La tarjeta **1** va primero, porque crea la base.
2. La **4** va antes que la **5**.
3. La **7** va antes que la **9** y la **10**.

---

## 👥 Reparto sugerido

| Persona | Área | Tarjetas |
|---------|------|----------|
| **A** | Interfaz, diseño y validación | Login, validación, modal, registro, animación y README |
| **B** | Datos y lógica | Estructura MVVM, ViewModel, Room, login contra Room y navegación |
| **Ambos** | Colaboración | Flujo de Git, cámara, pruebas, video y revisión final |
