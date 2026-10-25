package com.wolfcustom.vpn.util

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

/**
 * إدارة وتطبيق اللغات المتعددة (العربية RTL، الإنجليزية LTR، الفرنسية LTR)
 * متوافق مع Android 13+ Per-App Language ومع الإصدارات السابقة
 */
object LocaleHelper {
    private const val PREFS_NAME = "wolf_locale_prefs"
    private const val KEY_LANG = "app_language"

    const val LANG_AUTO = "auto"
    const val LANG_AR = "ar"
    const val LANG_EN = "en"
    const val LANG_FR = "fr"

    fun getLanguage(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANG, LANG_AUTO) ?: LANG_AUTO
    }

    fun setLanguage(context: Context, langCode: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LANG, langCode).apply()

        if (langCode == LANG_AUTO) {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.getEmptyLocaleList())
        } else {
            val appLocale = LocaleListCompat.forLanguageTags(langCode)
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
    }

    fun applyLocale(context: Context): Context {
        val lang = getLanguage(context)
        if (lang == LANG_AUTO) return context

        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocales(LocaleList(locale))
        } else {
            @Suppress("DEPRECATION")
            config.locale = locale
        }
        return context.createConfigurationContext(config)
    }
}