package com.wolfcustom.vpn

import android.app.Application
import android.util.Log

class ShizziApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i("WolfVPN", "Wolf Custom VPN Application Initialized")
    }
}