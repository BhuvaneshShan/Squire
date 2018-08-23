package com.shan.android.squire.Utils

import android.content.ComponentName
import android.content.Context.DEVICE_POLICY_SERVICE
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.util.Log
import com.shan.android.squire.DeviceAdminReceiver
import com.shan.android.squire.DeviceManager


object ProvisioningUtil{
    fun enableProfile(context: Context){
        Log.d("ProvisioningUtil", "Enabling profile")
        val manager = DeviceManager.getDpm(context)
        val componentName = DeviceManager.getComponentName(context)
        // This is the name for the newly created managed profile.
        manager.setProfileName(componentName, "Squire DO")
        // We enable the profile here.
        manager.setProfileEnabled(componentName)
    }
}