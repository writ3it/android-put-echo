package pl.poznan.put.fc.putecho.wifi.scanner

import android.net.wifi.ScanResult
import android.os.Parcel
import android.os.Parcelable

class WifiDevicesList(var data:ArrayList<ScanResult>?) : Parcelable{

    constructor(parcel: Parcel) : this() {
        data = parcel.createTypedArrayList(ScanResult.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(data)
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