package org.bitsandbits.indo

import androidx.compose.ui.window.ComposeUIViewController
import org.bitsandbits.indo.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { IndoEntryPoint() }