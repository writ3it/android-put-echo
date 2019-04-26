package pl.poznan.put.fc.putecho.wifi.scanner

import android.net.wifi.ScanResult

interface IWifiDeviceAdapter {
    fun isEnabled(): Boolean
    fun setEnabled(b: Boolean)
    fun Scan(): WifiDevicesList
}