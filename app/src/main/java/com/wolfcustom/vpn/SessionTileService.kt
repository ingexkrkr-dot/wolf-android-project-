package com.wolfcustom.vpn

import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi

/**
 * خدمة البلاطة السريعة في شريط الإشعارات (Quick Settings Tile)
 * للتحكم في تشغيل وإيقاف Wolf Custom VPN
 */
@RequiresApi(Build.VERSION_CODES.N)
class SessionTileService : TileService() {
    override fun onStartListening() {
        super.onStartListening()
        val tile = qsTile ?: return
        tile.state = Tile.STATE_INACTIVE
        tile.label = getString(R.string.tile_label)
        tile.updateTile()
    }

    override fun onClick() {
        super.onClick()
        val tile = qsTile ?: return
        val isNowActive = (tile.state != Tile.STATE_ACTIVE)
        tile.state = if (isNowActive) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        tile.updateTile()
    }
}