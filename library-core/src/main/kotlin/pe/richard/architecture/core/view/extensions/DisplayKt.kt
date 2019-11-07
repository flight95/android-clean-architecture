package pe.richard.architecture.core.view.extensions

import android.graphics.Point
import android.view.Display

fun Display.getSize(): Point = Point().also { getSize(it) }
