package pl.poznan.put.fc.putecho.wifi.list

import android.content.Context
import android.net.wifi.ScanResult
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.test_device_row.view.*
import pl.poznan.put.fc.putecho.R
import kotlin.collections.ArrayList
import pl.poznan.put.fc.putecho.wifi.scanner.*

class ScanResultAdapter(context: Context, layout_id:Int, id:Int,private var data:MutableList<RemoteDevice>) : ArrayAdapter<RemoteDevice>(context, layout_id,id, data) {

    var vi : LayoutInflater
    var resource : Int

    init{
        resource = id
        vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun Update(_data:List<RemoteDevice>){
        data.clear()
        data.addAll(_data)
        notifyDataSetChanged()
    }

    override fun getView(position:Int, convertView: View?, parent:ViewGroup):View?{
        var view = super.getView(position, convertView, parent)
        var textBox = view.findViewById<TextView>(resource)
        textBox.text = data[position].SSID
        var power = view.findViewById<TextView>(R.id.list_entry_power)
        power.text = data[position].Power
        return view
    }

    companion object {
        const val TAG = "ScanResultAdapter"
    }
}