package com.shan.android.squire

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent
import android.util.Log
import com.shan.android.squire.Utils.DisplayUtils
import java.util.logging.Logger
import android.content.ComponentName
import com.shan.android.squire.Utils.ProvisioningUtil


class DeviceAdminReceiver:DeviceAdminReceiver(){

    companion object {
        const val TAG = "DeviceAdminReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED -> onBootCompleted(context)
            DevicePolicyManager.ACTION_DEVICE_OWNER_CHANGED -> onDeviceOwnerChanged(context)
            else -> super.onReceive(context, intent)
        }
    }

    override fun onProfileProvisioningComplete(context: Context?, intent: Intent?) {
        super.onProfileProvisioningComplete(context, intent)
        Log.d(TAG, "Profile provisioning complete")
        if (context != null) {
            ProvisioningUtil.enableProfile(context)
        } else {
            Log.d(TAG, "context is null. couldnt enable profile!")
        }
    }

    override fun onEnabled(context: Context?, intent: Intent?) {
        super.onEnabled(context, intent)
        Log.d(TAG, "Owner enabled")
        if(context!=null)
            DeviceManager.grantPermissions(context)
    }

    override fun onDisabled(context: Context?, intent: Intent?) {
        super.onDisabled(context, intent)
        Log.d(TAG, "Owner disabled")
    }

    override fun onDisableRequested(context: Context?, intent: Intent?): CharSequence {
        return super.onDisableRequested(context, intent)
        Log.d(TAG, "Disable requested")
    }

    private fun onBootCompleted(context: Context){
        Log.d(TAG, "Boot completed")
    }

    private fun onProfileOwnerChanged(context: Context){
        Log.d(TAG, "Profile owner changed")
    }

    private fun onDeviceOwnerChanged(context: Context) {
        Log.d(TAG, "Device owner changed")
    }
}