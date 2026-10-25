package com.wolfcustom.vpn

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.wolfcustom.vpn.ui.WolfNetShareScreen
import com.wolfcustom.vpn.ui.MissingDependencyScreen
import com.wolfcustom.vpn.security.AppDependencyChecker
import com.wolfcustom.vpn.util.LocaleHelper

class MainActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context) {
        // تطبيق إعدادات اللغة المحفوظة (العربية RTL، الإنجليزية LTR، الفرنسية LTR)
        super.attachBaseContext(LocaleHelper.applyLocale(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. فحص سلامة وتوقيع التطبيق (Anti-Tamper & Integrity Check)
        AppDependencyChecker.verifyAppIntegrity(this)

        // 2. التحقق من تثبيت تطبيق core.vpn3jx.xtreme المطلوب عند تشغيل التطبيق
        val isTargetAppInstalled = AppDependencyChecker.isRequiredAppInstalled(this)

        if (!isTargetAppInstalled) {
            Toast.makeText(
                this,
                getString(R.string.required_app_missing_toast),
                Toast.LENGTH_LONG
            ).show()
        }

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isTargetAppInstalled) {
                        // إذا كان التطبيق مثبتاً، يستمر Wolf Custom VPN في التشغيل بشكل طبيعي
                        WolfNetShareScreen(
                            onLanguageChanged = { newLang ->
                                LocaleHelper.setLanguage(this@MainActivity, newLang)
                                recreate()
                            }
                        )
                    } else {
                        // إذا لم يكن التطبيق مثبتاً، منع المستخدم من الدخول وإظهار شاشة توجيه واضحة
                        MissingDependencyScreen(
                            onRetry = {
                                if (AppDependencyChecker.isRequiredAppInstalled(this@MainActivity)) {
                                    recreate()
                                } else {
                                    Toast.makeText(
                                        this@MainActivity,
                                        getString(R.string.required_app_missing_toast),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            onClose = { finish() }
                        )
                    }
                }
            }
        }
    }
}