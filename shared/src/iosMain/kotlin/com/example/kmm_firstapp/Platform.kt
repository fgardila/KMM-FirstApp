package com.example.kmm_firstapp

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.ios.*
import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platform: String = "Bienvenido señor " + UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Ios) {
    config(this)

    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}

actual fun initLogger() {
    Napier.base(DebugAntilog())
}