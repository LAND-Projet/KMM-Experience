package com.dardev.hellokmmapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform