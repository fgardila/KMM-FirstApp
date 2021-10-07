package com.example.kmm_firstapp

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.datetime.*
import kotlinx.serialization.*

class Greeting {

    val client = HttpClient() {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "HTTP Client", message = message)
                }
            }
            level = LogLevel.HEADERS
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }

    }.also { initLogger() }

    @Throws(Throwable::class)
    suspend fun greeting(): String {
        return "${getHello().random().string}! "
    }

    private suspend fun getHello(): List<Hello> {
        return client.get("https://gitcdn.link/cdn/KaterinaPetrova/greeting/7d47a42fc8d28820387ac7f4aaf36d69e434adc1/greetings.json")
    }
}

@Serializable
data class Hello(
    val string: String,
)

/*
fun dayUntilNewYear(): Int {
    val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
    val closestNewYear = LocalDate(today.year + 1, 1, 1)
    return today.daysUntil(closestNewYear)
}*/
