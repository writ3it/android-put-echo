package pl.poznan.put.fc.putecho.wifi.list

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import pl.poznan.put.fc.putecho.wifi.scanner.ScannerService
import pl.poznan.put.fc.putecho.wifi.scanner.WifiDevicesList

class ScanResultUpdater(val adapter:ScanResultAdapter) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG,"Odbiór listy wyników")
        val result = intent?.getParcelableExtra<WifiDevicesList>(ScannerService.SCANNER_RESULT_LIST)
        Log.i(TAG, "Liczba wyników "+result?.data?.count().toString() )
        if (result==null){
            return
        }
        adapter.Update(result.data)
    }
    companion object {
        const val TAG = "ScanResultUpdater"
    }
}