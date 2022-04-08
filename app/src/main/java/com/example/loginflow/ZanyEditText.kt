package com.example.loginflow

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputConnectionWrapper
import java.util.Random

class ZanyEditText : androidx.appcompat.widget.AppCompatEditText {
    private val r = Random()

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) {
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?) : super(context!!) {}

    fun setRandomBackgroundColor() {
        setBackgroundColor(
            Color.rgb(
                r.nextInt(256), r.nextInt(256), r
                    .nextInt(256)
            )
        )
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        return ZanyInputConnection(
            super.onCreateInputConnection(outAttrs),
            true
        )
    }

    private inner class ZanyInputConnection(target: InputConnection?, mutable: Boolean) :
        InputConnectionWrapper(target, mutable) {
        override fun sendKeyEvent(event: KeyEvent): Boolean {
            if (event.action == KeyEvent.ACTION_DOWN
                && event.keyCode == KeyEvent.KEYCODE_DEL
            ) {
                // Un-comment if you wish to cancel the backspace:
// return false;
            }
            return super.sendKeyEvent(event)
        }

        override fun deleteSurroundingText(
            beforeLength: Int,
            afterLength: Int
        ): Boolean { // magic: in latest Android, deleteSurroundingText(1, 0) will be called for backspace
            return if (beforeLength == 1 && afterLength == 0) { // backspace
                (sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                    && sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL)))
            } else super.deleteSurroundingText(beforeLength, afterLength)
        }
    }
}