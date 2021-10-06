package com.example.kmm_firstapp

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.android.*
import java.util.concurrent.TimeUnit

actual class Platform actual constructor() {
    actual val platform: String = "Bienvenido señor Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Android) {
    config(this)

    engine {
        threadsCount = 4
        pipelining = true
        connectTimeout = 100_000
    }
}

actual fun initLogger() {
    Napier.base(DebugAntilog())
}