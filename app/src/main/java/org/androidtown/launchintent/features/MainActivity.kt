package org.androidtown.launchintent.features

import android.content.Context
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.androidtown.launchintent.R

class MainActivity : AppCompatActivity() {
	
	// 버튼 리스트 객체 받음 cwj_주석_2021-03-12_오전 10:19
	private lateinit var mainListener: MainListener
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		initialize()
//		val manager = getSystemService(Context.USB_SERVICE) as UsbManager
//		val deviceList : HashMap<String, UsbDevice> = manager.deviceList
//		var device = deviceList["deviceName"]
	}
	
	// 버튼 구성 메서드 cwj_주석_2021-03-12_오전 10:31
	private fun initialize() {
		
		mainListener = MainListener(this)
		mainListener.initializeListeners()
		
		// no connected device 라고 나오는 레이아웃 클릭시 동작
		// cwj_주석_2021-03-12_오전 10:46
		aMain_device_clo.setOnClickListener {
			//Toast.makeText(this,"refreshed", Toast.LENGTH_LONG).show()
			
			// USB_SERVICE 접근을 위한 UsbManager 객체 cwj_주석_2021-03-12_오전 10:48
			val manager = getSystemService(Context.USB_SERVICE) as UsbManager
			
			// USB_SERVICE 에 접근하여 deviceList 받아옴
			// cwj_주석_2021-03-12_오전 10:48
			val deviceList = manager.deviceList
			/*
			deviceList.forEach {
				Toast.makeText(this,deviceList.toString(), Toast.LENGTH_LONG).show()
				//
				aMain_log_etv.setText(deviceList.toString())
			}*/
			
			// toast 메세지를 위한 문자열 cwj_주석_2021-03-12_오후 12:03
			var str = ""
			
			// deviceList 에서 각각의 값을 검사함 cwj_주석_2021-03-12_오후 12:03
			deviceList.values.forEach {
				// it = devicelist
				// .vendorId 로 vendorId 에 접근하고 타겟할 vendorId 입력 후 동작
				// cwj_주석_2021-03-12_오후 12:25
				if (it.vendorId == 16700) {
					//do something
					aMain_device_tv.text = deviceList.values.firstOrNull()!!.productName
					aMain_status_iv.setImageDrawable(getDrawable(R.drawable.ic_activity_main_status_connected))
					Toast.makeText(this, str + "test", Toast.LENGTH_LONG).show()
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