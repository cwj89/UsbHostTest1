package org.androidtown.launchintent.features

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import org.androidtown.launchintent.R

class MainActivity : AppCompatActivity() {

    private lateinit var mainListener : MainListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
/*
        val manager = getSystemService(Context.USB_SERVICE) as UsbManager
        val deviceList : HashMap<String, UsbDevice> = manager.deviceList
        var device = deviceList["deviceName"]
        */

    }

    private fun initialize(){
        mainListener = MainListener(this)
        mainListener.initializeListeners()

        aMain_device_clo.setOnClickListener{
            //Toast.makeText(this,"refreshed", Toast.LENGTH_LONG).show()
            val manager = getSystemService(Context.USB_SERVICE) as UsbManager
            val deviceList = manager.getDeviceList()
            /*
            deviceList.forEach {
                Toast.makeText(this,deviceList.toString(), Toast.LENGTH_LONG).show()
                //
                aMain_log_etv.setText(deviceList.toString())
            }*/
            var str = ""
            deviceList.values.forEach {
                if(it.vendorId == 1155){
                    //do something
                }
                str += it.vendorId.toString() + "  ,  1 "
            }
            //Toast.makeText(this,deviceList.values.firstOrNull()!!.deviceName.toString() , Toast.LENGTH_LONG).show()


            Toast.makeText(this, str + " dfda " , Toast.LENGTH_LONG).show()

            /*
            if(device != null){
                aMain_device_tv.text = device.toString()
                aMain_status_iv.setImageDrawable(getDrawable(R.drawable.ic_activity_main_status_connected))

                Toast.makeText(this, device.toString(), Toast.LENGTH_LONG).show()
            }else{
                aMain_output_tv.text = null
                aMain_status_iv.setImageDrawable(getDrawable(R.drawable.ic_activity_main_status_disconnected))

                Toast.makeText(this, "device variable has nothing", Toast.LENGTH_LONG).show()
            }*/


        }
    }
}