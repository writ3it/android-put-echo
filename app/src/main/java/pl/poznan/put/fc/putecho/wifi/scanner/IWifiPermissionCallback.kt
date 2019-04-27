package pl.poznan.put.fc.putecho.wifi.scanner

interface IWifiPermissionCallback {
    fun onGranted()
    fun onDeclined()
}