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
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UISwitch

@BetaInteropApi
@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun NativeToggle(modifier: Modifier, value: Boolean, onClick: (value: Boolean) -> Unit) {
    UIKitView(
        modifier = modifier,
        factory = {
            val switch = object : UISwitch(frame = CGRectZero.readValue()) {

                @ObjCAction
                fun onClickInternal() {
                    onClick(on)
                }
            }
            switch.addTarget(
                switch,
                NSSelectorFromString(switch::onClickInternal.name),
                UIControlEventTouchUpInside
            )
            switch
        },
        update = { view ->
            view.on = value
        },
    )
}