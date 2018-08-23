package com.shan.android.squire

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.location.LocationListener
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.content.ContextCompat
import android.util.Log


object LocationProvider {

    private var TAG: String = "LocationProvider"

    private var currentLoc: Location? = null
    private var isRegistered = false

    fun register(context: Context) {
        if(requestPermission(context)) {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1.0f, locationListener)
                isRegistered = true
            } catch (se: SecurityException) {
                Log.e(TAG, "Permission exception:$se")
            }
        }
    }

    private fun requestPermission(context: Context): Boolean{
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "GPS Permission not granted!")
            return false
        }
        return true
    }


    fun unregister(context: Context){
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.removeUpdates(locationListener)
        isRegistered = false
    }

    fun getLocation(context: Context): Location?{
        if(!isRegistered){
            register(context)
        }
        return currentLoc
    }

    private val locationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            // Called when a new location is found by the network location provider.
            currentLoc = location
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}
    }
}