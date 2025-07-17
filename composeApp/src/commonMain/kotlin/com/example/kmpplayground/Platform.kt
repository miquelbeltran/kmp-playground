package com.example.kmpplayground

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform