package org.bitsandbits.indo

import androidx.compose.ui.window.ComposeUIViewController
import com.bitsandbits.presentation.component.IndoScaffold
import org.bitsandbits.indo.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { IndoScaffold() }