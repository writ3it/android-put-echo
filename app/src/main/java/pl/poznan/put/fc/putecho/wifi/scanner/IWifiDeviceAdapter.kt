package pl.poznan.put.fc.putecho.wifi.scanner

interface IWifiDeviceAdapter {
    fun isEnabled(): Boolean
    fun setEnabled(b: Boolean)
}