package pl.poznan.put.fc.putecho.wifi.scanner

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.net.wifi.ScanResult
import android.support.v4.content.LocalBroadcastManager

private const val ACTION_UPDATE = "pl.poznan.put.fc.putecho.wifi.scanner.action.UPDATE"

// TODO: Rename parameters
//private const val EXTRA_PARAM1 = "pl.poznan.put.fc.putecho.wifi.scanner.extra.PARAM1"
private const val EXTRA_PARAM2 = "pl.poznan.put.fc.putecho.wifi.scanner.extra.PARAM2"

/**
 * Responsibility of Scanner service is delivering data about APs
 */
class ScannerService : IntentService("ScannerService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_UPDATE -> {
                handleUpdate()
            }
        }
    }

    lateinit var device:WifiDevice

    override fun onCreate() {
        super.onCreate()
        device = WifiDevice()
    }


    private fun handleUpdate() {
        enableWifi()
        scan()
    }

    private fun scan() {
        val scanResults = device.Scan()
        val localIntent = Intent(BROADCAST_LIST_ACTION).apply {
            putExtra(SCANNER_RESULT_LIST,scanResults)
        }
        LocalBroadcastManager.getInstance(baseContext).sendBroadcast(localIntent)
    }

    private fun enableWifi() {
        if (!device.isEnabled()){
            device.setEnabled(true)
        }
    }


    companion object {
        const val BROADCAST_LIST_ACTION = "pl.poznan.put.fc.putecho.wifi.scanner.action.BROADCAST"
        const val SCANNER_RESULT_LIST = "pl.poznan.put.fc.putecho.wifi.scanner.action.UPDATE.RESULT"
        const val EMPTY_RESULT = ArrayList<ScanResult>(0)
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun Update(context: Context) {
            val intent = Intent(context, ScannerService::class.java).apply {
                action = ACTION_UPDATE
                //putExtra(EXTRA_PARAM1, param1)
            }
            context.startService(intent)
        }

    }
}
