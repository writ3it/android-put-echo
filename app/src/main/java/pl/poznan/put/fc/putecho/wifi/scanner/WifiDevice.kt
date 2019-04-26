package pl.poznan.put.fc.putecho.wifi.scanner

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager

/**
 * Adapter for WifiManager
 */
class WifiDevice( context:Context) : IWifiDeviceAdapter{

    override fun Scan(): WifiDevicesList {
        if (_manager.startScan()){
            return WifiDevicesList(ArrayList(_manager.scanResults))
        }
        return WifiDevicesList(ArrayList(0))
    }


    val _manager:WifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    override fun isEnabled(): Boolean {
        return _manager.isWifiEnabled
    }

    override fun setEnabled(b: Boolean) {
        _manager.isWifiEnabled = b
    }
}