package com.dardev.koinapp

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val repository: MainRepository
): ViewModel() {

    fun doNetworkCall(){
        repository.doNetworkCall()
    }
}