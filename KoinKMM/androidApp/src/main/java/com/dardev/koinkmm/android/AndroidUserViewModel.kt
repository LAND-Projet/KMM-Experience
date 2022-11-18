package com.dardev.koinkmm.android

import androidx.lifecycle.ViewModel
import com.dardev.koinkmm.data.DefaultData
import com.dardev.koinkmm.data.IUserRepository

class AndroidUserViewModel(private val repository: IUserRepository) : ViewModel() {

    fun sayHello() : String{
        val name = DefaultData.DEFAULT_USER.name
        val foundUser = repository.findUser(name)
        return foundUser?.let { "Hello '$it' from $this" } ?: "User '$name' not found!"
    }
}