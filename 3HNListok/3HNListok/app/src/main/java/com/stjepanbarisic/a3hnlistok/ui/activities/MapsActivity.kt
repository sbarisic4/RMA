package com.stjepanbarisic.a3hnlistok.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.stjepanbarisic.a3hnlistok.R
import com.stjepanbarisic.a3hnlistok.data.models.persistence.FootballTeam
import com.stjepanbarisic.a3hnlistok.databinding.ActivityMapsBinding
import com.stjepanbarisic.a3hnlistok.ui.fragments.BUNDLE_KEY_HOST_FOOTBALL_TEAM

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val hostFootballTeam
        get() = intent.extras?.getParcelable<FootballTeam>(
            BUNDLE_KEY_HOST_FOOTBALL_TEAM
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in host stadium and move the camera
        val stadiumLocation = hostFootballTeam?.location
        val location = LatLng(stadiumLocation?.latitude ?: 0.0, stadiumLocation?.longitude ?: 0.0)
        mMap.addMarker(
            MarkerOptions().position(location)
                .title(getString(R.string.host_football_team_is, hostFootballTeam?.name))
        )
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    location.latitude,
                    location.longitude
                ), 17.0f
            )
        )
    }
}