package com.dardev.koinkmm

import com.dardev.koinkmm.presenter.KMPUserPresenter
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KMPUserPresenterHelper : KoinComponent {

    private val userPresenter : KMPUserPresenter by inject()

    fun sayHello(): String = userPresenter.sayHello()
}