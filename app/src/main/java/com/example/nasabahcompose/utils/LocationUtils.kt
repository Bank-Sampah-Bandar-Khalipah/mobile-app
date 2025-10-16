package com.example.nasabahcompose.utils

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
fun getCurrentLocation(context: Context, onLocationReceived: (String) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            val lat = location.latitude
            val lng = location.longitude
            onLocationReceived("$lat, $lng") // bisa diganti geocoder untuk alamat lengkap
        } else {
            onLocationReceived("Lokasi tidak ditemukan")
        }
    }
}
