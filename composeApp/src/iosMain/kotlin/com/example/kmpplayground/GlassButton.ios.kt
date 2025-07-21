package com.example.kmpplayground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIButton
import platform.UIKit.UIButtonConfiguration
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UIControlStateNormal

@BetaInteropApi
@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun GlassButton(
    modifier: Modifier,
    label: String,
    onClick: () -> Unit
) {
    UIKitView(
        modifier = modifier,
        factory = {
            val button = object : UIButton(frame = CGRectZero.readValue()) {

                @ObjCAction
                fun onClickInternal() {
                    onClick()
                }
            }
            button.addTarget(
                button,
                NSSelectorFromString(button::onClickInternal.name),
                UIControlEventTouchUpInside
            )
            button.configuration = UIButtonConfiguration.borderedButtonConfiguration()
            button
        },
        update = { view ->
            view.setTitle(label, forState = UIControlStateNormal)
        },
    )
}