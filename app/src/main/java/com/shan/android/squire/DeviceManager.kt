package com.shan.android.squire

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.provider.Settings
import java.util.jar.Manifest

object DeviceManager{

    fun getDpm(context: Context): DevicePolicyManager {
        return context.getSystemService(android.content.Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
    }

    fun getComponentName(context: Context): ComponentName {
        return ComponentName(context.applicationContext, DeviceAdminReceiver::class.java)
    }

    fun enableLocation(context: Context){
        getDpm(context).setSecureSetting(
                getComponentName(context),
                Settings.Secure.LOCATION_MODE,
                Settings.Secure.LOCATION_MODE_HIGH_ACCURACY.toString())
    }

    fun disableLocation(context: Context){
        getDpm(context).setSecureSetting(
                getComponentName(context),
                Settings.Secure.LOCATION_MODE,
                Settings.Secure.LOCATION_MODE_OFF.toString())
    }

    fun grantPermissions(context: Context){
        getDpm(context).setPermissionGrantState(
                getComponentName(context),
                context.packageName,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                DevicePolicyManager.PERMISSION_GRANT_STATE_GRANTED
        )
    }
}