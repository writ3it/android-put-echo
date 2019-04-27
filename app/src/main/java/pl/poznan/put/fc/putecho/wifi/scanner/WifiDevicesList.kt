package pl.poznan.put.fc.putecho.wifi.scanner

import android.net.wifi.ScanResult
import android.os.Parcel
import android.os.Parcelable
import kotlin.collections.ArrayList

class WifiDevicesList() : Parcelable{
    lateinit var data:List<RemoteDevice>

    constructor(_data:List<ScanResult>) : this() {
        data = _data.map { x -> RemoteDevice(x) }
    }

    constructor(parcel: Parcel) : this(emptyList()) {
        data = parcel.createTypedArrayList(RemoteDevice.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedArray(data.toTypedArray(), flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WifiDevicesList> {
        override fun createFromParcel(parcel: Parcel): WifiDevicesList {
            return WifiDevicesList(parcel)
        }

        override fun newArray(size: Int): Array<WifiDevicesList?> {
            return arrayOfNulls(size)
        }
    }


}