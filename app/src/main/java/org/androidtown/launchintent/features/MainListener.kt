package org.androidtown.launchintent.features

import android.app.Activity
import android.content.Intent
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.androidtown.launchintent.R

class MainListener(private val activity: Activity) {
	fun initializeListeners() {
		activity.aMain_btn_option_wireless.setOnClickListener {
			Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show()
			var intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
			activity.startActivity(intent)
		}
		
		val firstSpinner: Spinner = activity.aMain_key_mapping_first_spnr
		val secondSpinner: Spinner = activity.aMain_key_mapping_second_spnr
		val thirdSpinner: Spinner = activity.aMain_key_mapping_third_spnr
		ArrayAdapter.createFromResource(
				activity,
				R.array.action_array,
				R.layout.support_simple_spinner_dropdown_item
		).also { adapter ->
			adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
			firstSpinner.adapter = adapter
			secondSpinner.adapter = adapter
			thirdSpinner.adapter = adapter
		}
		
		activity.aMain_key_mapping_save_btn.setOnClickListener {
			activity.aMain_output_tv.text =
				"${firstSpinner.selectedItem} / ${secondSpinner.selectedItem} / ${thirdSpinner.selectedItem} has saved"
		}
		
		
	}
}