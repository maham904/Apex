package com.apex.codeassesment.ui.location

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.apex.codeassesment.R
import com.apex.codeassesment.databinding.ActivityLocationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


// TODO (Optional Bonus 8 points): Calculate distance between 2 coordinates using phone's location
class LocationActivity : AppCompatActivity() {
    private var distanceInKilometers: Double = 0.0
    private lateinit var longitudeRandomUser: String
    private lateinit var latitudeRandomUser: String
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        latitudeRandomUser = intent.getStringExtra("user-latitude-key")!!
        longitudeRandomUser = intent.getStringExtra("user-longitude-key")!!

        binding.locationRandomUser.text =
            getString(R.string.location_random_user, latitudeRandomUser, longitudeRandomUser)
        requestLocationUpdates()
        binding.locationCalculateButton.setOnClickListener {
            Toast.makeText(
                this,
                "TODO (8): Bonus - Calculate distance between 2 coordinates using phone's location: $distanceInKilometers",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener {
                val latitude = it.latitude
                val longitude = it.longitude
                distanceInKilometers = DistanceHelper().distance(
                    latitude,
                    longitude,
                    latitudeRandomUser.toDouble(),
                    longitudeRandomUser.toDouble(),
                    'K'
                )
            }
        }
    }
}
