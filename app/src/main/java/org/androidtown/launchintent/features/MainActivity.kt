package org.androidtown.launchintent.features

import android.content.Context
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.androidtown.launchintent.R

class MainActivity : AppCompatActivity() {
	private lateinit var mainListener: MainListener
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		initialize()
	//val manager = getSystemService(Context.USB_SERVICE) as UsbManager
	//val deviceList : HashMap<String, UsbDevice> = manager.deviceList
	//var device = deviceList["deviceName"]
		
	}
	private fun initialize() {
		// 코드 업데이트 테스트 cwj_주석_2021-03-08_오후 5:26
		// 코드 업데이트 테스트 2 cwj_주석_2021-03-08_오후 5:40
		// 코드 업데이트 테스트 3 cwj_주석_2021-03-09_오전 10:07
		// 코드 업데이트 테스트 4
		// 코드 업데이트 테스트 5 cwj_주석_2021-03-09_오전 11:10
		// 코드 업데이트 테스트 6 cwj_주석_2021-03-09_오전 11:50
		mainListener = MainListener(this)
		mainListener.initializeListeners()
		
		aMain_device_clo.setOnClickListener {
			//Toast.makeText(this,"refreshed", Toast.LENGTH_LONG).show()
			val manager = getSystemService(Context.USB_SERVICE) as UsbManager
			val deviceList = manager.deviceList
			/*
			deviceList.forEach {
				Toast.makeText(this,deviceList.toString(), Toast.LENGTH_LONG).show()
				//
				aMain_log_etv.setText(deviceList.toString())
			}*/
			var str = ""
			Toast.makeText(this, str, Toast.LENGTH_LONG).show()
			
			deviceList.values.forEach {
				if (it.vendorId == 1155) {
					//do something
				}
				str += it.vendorId.toString() + "  ,  1 "
			}
			//Toast.makeText(this,deviceList.values.firstOrNull()!!.deviceName.toString() , Toast.LENGTH_LONG).show()
			
			
			Toast.makeText(this, str + " dfda ", Toast.LENGTH_LONG).show()
			
			/*
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