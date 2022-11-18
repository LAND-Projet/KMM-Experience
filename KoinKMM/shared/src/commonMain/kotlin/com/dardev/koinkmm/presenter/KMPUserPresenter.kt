package com.dardev.koinkmm.presenter

import com.dardev.koinkmm.data.DefaultData
import com.dardev.koinkmm.data.IUserRepository

class KMPUserPresenter(private val repository: IUserRepository) {

    fun sayHello() : String {
        val name = DefaultData.DEFAULT_USER.name
        val foundUser = repository.findUser(name)
        return foundUser?.let { "Hello '$it' from $this" } ?: "User '$name' not found!"
    }
}