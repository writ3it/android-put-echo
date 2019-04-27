package pl.poznan.put.fc.putecho.wifi.scanner

import android.net.wifi.ScanResult
import android.os.Parcel
import android.os.Parcelable

class RemoteDevice() : Parcelable {
    var ssid:String = ""
    var SSID:String
        get() = ssid
        set(value){
            ssid = value
        }
    lateinit var Power: String

    constructor(parcel: Parcel) : this() {
        val ssid:String? = parcel.readString()
        if (ssid!=null){
            SSID = ssid
        }
        Power = parcel.readString()
    }

    constructor(result:ScanResult?) : this() {
        SSID = result!!.SSID
        Power = result.level.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(SSID)
        parcel.writeString(Power)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RemoteDevice> {
        override fun createFromParcel(parcel: Parcel): RemoteDevice {
            return RemoteDevice(parcel)
        }

        override fun newArray(size: Int): Array<RemoteDevice?> {
            return arrayOfNulls(size)
        }
    }
}
