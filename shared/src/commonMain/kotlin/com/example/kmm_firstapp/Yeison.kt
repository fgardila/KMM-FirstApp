package com.example.kmm_firstapp

class Yeison {
    fun name(): String {
        return "Yeison Sanchez ${platformName()}"
    }
}

expect fun platformName(): String