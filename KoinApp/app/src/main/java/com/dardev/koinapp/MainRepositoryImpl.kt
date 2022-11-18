package com.dardev.koinapp

class MainRepositoryImpl(
    private val api: MyApi
): MainRepository {
    override fun doNetworkCall() {
        api.callApi()
    }
}