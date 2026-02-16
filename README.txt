ANDROID APP (Android Studio)

Requisitos:
- Android Studio (Giraffe o más nuevo recomendado)
- SDK 34

Cómo abrir:
1) Abrir carpeta: android-app
2) Esperar a que Gradle sincronice.
3) Cambiar BASE_URL en:
   app/src/main/java/com/tienda/app/RetrofitClient.kt
   - Emulador: http://10.0.2.2:8080/
   - Celular físico: http://IP_DE_TU_PC:8080/

Cómo probar:
- Arranca primero el backend
- Abre la app, verás productos
- Agrega al carrito, ver total y presiona PAGAR
- Luego revisa:
  - Admin web: http://localhost:8080/admin/sales
  - Logs backend: "✅ NUEVA COMPRA REGISTRADA ..."
