package org.androidtown.launchintent.features

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.androidtown.launchintent.R

private const val ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION"

class MainActivity : AppCompatActivity() {
	
	// 버튼 리스트 객체 받음 cwj_주석_2021-03-12_오전 10:19
	private lateinit var mainListener: MainListener
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		lateinit var device: UsbDevice
		
		val usbManager = getSystemService(Context.USB_SERVICE) as UsbManager
		val permissionIntent = PendingIntent
			.getBroadcast(this, 0, Intent(ACTION_USB_PERMISSION), 0)
		val filter = IntentFilter(ACTION_USB_PERMISSION)
		val deviceList = usbManager.deviceList
		
		deviceList.values.forEach {
			// .vendorId 로 vendorId 에 접근하고 타겟할 vendorId 입력 후 동작
			// cwj_주석_2021-03-12_오후 12:25
			if (it.vendorId == 1155) {
				//do something
				device = it.apply {  }
				aMain_device_tv.text = it.productName
				aMain_status_iv.setImageDrawable(getDrawable(R.drawable.ic_activity_main_status_connected))
				usbManager.requestPermission(device, permissionIntent)
				registerReceiver(usbReceiver, filter)
				Log.d("device_fined", "$device")
			}
		}
		
		Log.d("device_fined", "$device")
		
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
			lateinit var device: UsbDevice
			
			// deviceList 에서 각각의 값을 검사함 cwj_주석_2021-03-12_오후 12:03
			deviceList.values.forEach {
				// .vendorId 로 vendorId 에 접근하고 타겟할 vendorId 입력 후 동작
				// cwj_주석_2021-03-12_오후 12:25
				if (it.vendorId == 1155) {
					//do something
					device = it.apply {  }
					aMain_device_tv.text = it.productName
					aMain_status_iv.setImageDrawable(getDrawable(R.drawable.ic_activity_main_status_connected))
					Log.d("device_fined", "$device")
				}
			}
			//Toast.makeText(this,deviceList.values.firstOrNull()!!.deviceName.toString() , Toast.LENGTH_LONG).show()
		}
	}
	
	private val usbReceiver = object : BroadcastReceiver() {
		
		override fun onReceive(context: Context, intent: Intent) {
			if (ACTION_USB_PERMISSION == intent.action) {
				synchronized(this) {
					val device: UsbDevice? = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
					
					if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
						device?.apply {
							//call method to set up device communication
							Log.d("device_fined", "$device")
						}
					} else {
						Log.d("device_fined", "permission denied for device $device")
					}
				}
			}
		}
	}
	
}