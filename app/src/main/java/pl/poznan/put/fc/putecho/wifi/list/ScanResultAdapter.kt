package pl.poznan.put.fc.putecho.wifi.list

import android.content.Context
import android.net.wifi.ScanResult
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.test_device_row.view.*
import pl.poznan.put.fc.putecho.R

class ScanResultAdapter(context: Context, id:Int,private val data:ArrayList<ScanResult>) : ArrayAdapter<ScanResult>(context, id, data) {

    override fun getView(position:Int, convertView: View?, parent:ViewGroup):View?{
        val item = data[position]
        val textBox = convertView?.findViewById<TextView>(R.id.list_entry_title)
        textBox.text = item.SSID
        return convertView
    }
}