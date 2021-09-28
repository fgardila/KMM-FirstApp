package com.example.kmm_firstapp

actual class Platform actual constructor() {
    actual val platform: String = "Bienvenido señor Android ${android.os.Build.VERSION.SDK_INT}"
}