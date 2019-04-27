package pl.poznan.put.fc.putecho

import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import pl.poznan.put.fc.putecho.wifi.scanner.ScannerService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import pl.poznan.put.fc.putecho.wifi.list.ScanResultAdapter
import pl.poznan.put.fc.putecho.wifi.list.ScanResultUpdater
import pl.poznan.put.fc.putecho.wifi.scanner.WifiDevice

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initalize()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun initalize() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(WifiDevice.REQUIRED_PERMISSION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(WifiDevice.REQUIRED_PERMISSION),
                WifiDevice.REQUIRED_PERMISSION_ID)
            return
        }
        val adapter = ScanResultAdapter(
            applicationContext,
            R.layout.test_device_row,
            R.id.list_entry_title,
            ScannerService.EMPTY_RESULT.toMutableList()
        )
        testListOutput.adapter = adapter
        val updater = ScanResultUpdater(adapter)
        val handler = Handler()
        val period = object : Runnable{
            override fun run(){
                ScannerService.Update(context = applicationContext, receiver = updater)
                handler.postDelayed(this, 10000)
            }
        }
        period.run()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode != WifiDevice.REQUIRED_PERMISSION_ID) {
            return
        }
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            initalize()
        } else {
            Toast.makeText(this, "Uprawnienia są wymagane do działania aplikacji", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
