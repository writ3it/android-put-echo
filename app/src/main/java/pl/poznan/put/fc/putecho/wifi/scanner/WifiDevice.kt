package pl.poznan.put.fc.putecho.wifi.scanner

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Build
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.util.Log


/**
 * Adapter for WifiManager
 */
class WifiDevice( context:Context) : IWifiDeviceAdapter{



    override fun Scan(): WifiDevicesList {
        if (_manager.startScan()){
            Log.i(TAG,"get scan result")
            val result = _manager.scanResults
            return WifiDevicesList( result )
        }
        Log.i(TAG,"scan not started")
        return WifiDevicesList(emptyList())
    }


    val _manager:WifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    override fun isEnabled(): Boolean {
        return _manager.isWifiEnabled
    }

    override fun setEnabled(b: Boolean) {
        _manager.isWifiEnabled = b
    }

    companion object {
        const val REQUIRED_PERMISSION = "android.permission.ACCESS_FINE_LOCATION"
        const val REQUIRED_PERMISSION_ID = 2137
        const val TAG = "WifiDevice"
    }
}