package pl.poznan.put.fc.putecho.wifi.list

import android.content.Context
import android.net.wifi.ScanResult
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.test_device_row.view.*
import pl.poznan.put.fc.putecho.R
import kotlin.collections.ArrayList
import pl.poznan.put.fc.putecho.wifi.scanner.*

class ScanResultAdapter(context: Context, id:Int,private val data:List<RemoteDevice>) : ArrayAdapter<RemoteDevice>(context, id, data) {

    override fun getView(position:Int, convertView: View?, parent:ViewGroup):View?{
        val item = data[position]
        val textBox = convertView?.findViewById<TextView>(R.id.list_entry_title)
        textBox?.text = item.SSID
        return convertView
    }
}