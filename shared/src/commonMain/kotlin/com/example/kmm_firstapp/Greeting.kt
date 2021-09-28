package com.example.kmm_firstapp

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}