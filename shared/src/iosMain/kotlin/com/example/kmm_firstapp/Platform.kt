package com.example.kmm_firstapp

import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platform: String = "Bienvenido señor " + UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}