package com.dardev.koinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.dardev.koinapp.ui.theme.KoinAppTheme
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityRetainedScope
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope


class MainActivity() : ComponentActivity(), AndroidScopeComponent {
    override val scope: Scope by activityScope()
    private val hello by inject<String> (named("bye"))

    // Lazy Injection
    // private val api by inject<MyApi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(hello)
        setContent {
            KoinAppTheme {
                val viewModel = getViewModel<MainViewModel>()
                viewModel.doNetworkCall()
            }
        }
    }
}