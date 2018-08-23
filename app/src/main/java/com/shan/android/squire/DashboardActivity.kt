package com.shan.android.squire

import android.app.admin.DevicePolicyManager
import android.bluetooth.BluetoothClass
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var presenter: DashboardActivityPresenter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        DeviceManager.grantPermissions(applicationContext)

        loc_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                DeviceManager.enableLocation(applicationContext)
            }else{
                DeviceManager.disableLocation(applicationContext)
            }
        }

        fetch_btn.setOnClickListener(View.OnClickListener {
            var loc = LocationProvider.getLocation(applicationContext)
            var loc_text = "Not Available"
            if(loc!=null) {
               loc_text = "${loc.latitude}, ${loc.longitude}"
            }
            location_textview.text = loc_text
        })
    }

    override fun onResume() {
        super.onResume()
        LocationProvider.register(applicationContext)
    }

    override fun onPause() {
        super.onPause()
        LocationProvider.unregister(applicationContext)
    }
}
