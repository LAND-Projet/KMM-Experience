package com.dardev.yanotekmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform