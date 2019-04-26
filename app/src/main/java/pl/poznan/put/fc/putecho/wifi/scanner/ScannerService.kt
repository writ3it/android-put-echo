package pl.poznan.put.fc.putecho.wifi.scanner

import android.app.IntentService
import android.content.Intent
import android.content.Context

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun enableWifi() {
        if (!device.isEnabled()){
            device.setEnabled(true)
        }
    }


    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun Update(context: Context, param1: String, param2: String) {
            val intent = Intent(context, ScannerService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

    }
}
