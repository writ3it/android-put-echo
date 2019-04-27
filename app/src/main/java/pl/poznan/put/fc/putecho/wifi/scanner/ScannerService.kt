package pl.poznan.put.fc.putecho.wifi.scanner

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.Context
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.support.v4.content.LocalBroadcastManager
import android.util.Log

private const val ACTION_UPDATE = "pl.poznan.put.fc.putecho.wifi.scanner.action.UPDATE"

// TODO: Rename parameters
//private const val EXTRA_PARAM1 = "pl.poznan.put.fc.putecho.wifi.scanner.extra.PARAM1"
private const val EXTRA_PARAM2 = "pl.poznan.put.fc.putecho.wifi.scanner.extra.PARAM2"

/**
 * Responsibility of Scanner service is delivering data about APs
 */
class ScannerService : IntentService("ScannerService") {

    override fun onHandleIntent(intent: Intent?) {
        Log.i(TAG, "onHandleIntent")
        when (intent?.action) {
            ACTION_UPDATE -> {
                handleUpdate()
            }
        }
    }

    lateinit var device:WifiDevice

    override fun onCreate() {
        super.onCreate()

        device = WifiDevice(applicationContext)
    }


    private fun handleUpdate() {
        Log.i(TAG, "handleUpdate")
        enableWifi()
        scan()
    }

    private fun scan() {
        Log.i(TAG, "scan")
        val scanResults = device.Scan()
        val localIntent = Intent(BROADCAST_LIST_ACTION).apply {
            Log.i(TAG, "create data elements count = "+scanResults.data.size.toString())
            putExtra(SCANNER_RESULT_LIST,scanResults)
        }
        Log.i(TAG, "broadcast")
        LocalBroadcastManager.getInstance( applicationContext).sendBroadcast(localIntent)
    }

    private fun enableWifi() {
        if (!device.isEnabled()){
            device.setEnabled(true)
        }
    }


    companion object {
        const val BROADCAST_LIST_ACTION = "pl.poznan.put.fc.putecho.wifi.scanner.action.BROADCAST"
        const val SCANNER_RESULT_LIST = "pl.poznan.put.fc.putecho.wifi.scanner.action.UPDATE.RESULT"
        const val TAG = "ScannerService"
        val EMPTY_RESULT = emptyList<RemoteDevice>()
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        @JvmStatic
        fun Update(context: Context, receiver:BroadcastReceiver? = null) {

            val intent = Intent(context, ScannerService::class.java).apply {
                action = ACTION_UPDATE
                //putExtra(EXTRA_PARAM1, param1)
            }

            Log.i(TAG, "statUpdateService")
            context.startService(intent)
            if (receiver != null){
                Log.i(TAG, "set broadcast receiver")
                val filter = IntentFilter()
                filter.addAction(BROADCAST_LIST_ACTION)
                LocalBroadcastManager.getInstance(context).registerReceiver(receiver,filter)
            }
        }

    }
}
