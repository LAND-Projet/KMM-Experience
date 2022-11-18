package com.dardev.ktorapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform