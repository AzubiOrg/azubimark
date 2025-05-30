package me.kelexine.azubimark

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

object ThemeUtils {
    @ColorInt
    fun getMaterialYouColor(context: Context, @AttrRes attr: Int): Int {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(attr, typedValue, true)
        return typedValue.data
    }
    
    @ColorInt
    fun adjustAlpha(@ColorInt color: Int, factor: Float): Int {
        val alpha = (Color.alpha(color) * factor).toInt().coerceIn(0, 255)
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color))
    }
    
    fun shouldUseLightStatusBar(context: Context): Boolean {
    // Get the actual toolbar color from your theme
    val typedValue = TypedValue()
    context.theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, typedValue, true)
    val toolbarColor = typedValue.data
    
    // Calculate perceived brightness
    val brightness = (Color.red(toolbarColor) * 299 + 
                     Color.green(toolbarColor) * 587 + 
                     Color.blue(toolbarColor) * 114) / 1000
                     
    // If the toolbar is light (brightness > 128), use dark icons
    return brightness > 128
    }
    
    @ColorInt
    fun adjustBrightness(@ColorInt color: Int, factor: Float): Int {
        val r = (Color.red(color) * factor).toInt().coerceIn(0, 255)
        val g = (Color.green(color) * factor).toInt().coerceIn(0, 255)
        val b = (Color.blue(color) * factor).toInt().coerceIn(0, 255)
        return Color.rgb(r, g, b)
    }
    
    @ColorInt
    fun getContrastColor(@ColorInt color: Int): Int {
        // Calculate luminance - a measure of brightness
        val luminance = (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return if (luminance > 0.5) Color.BLACK else Color.WHITE
    }
}
