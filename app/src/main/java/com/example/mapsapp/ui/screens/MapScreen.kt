package com.example.mapsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mapsapp.data.Marker
import com.example.mapsapp.viewmodels.MyViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen() {
    val myViewModel = MyViewModel()
    val markers: List<Marker> by myViewModel.markersList.observeAsState(emptyList())

    val itb = LatLng(41.430223, 2.151027)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(itb, 18f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        cameraPositionState = cameraPositionState,
        onMapClick = { latLng ->
            val newMarker = Marker(
                lat = latLng.latitude.toString(),
                lng = latLng.longitude.toString(),
                title = "Nou marcador",
                snipet = "Creat pel clic al mapa"
            )
        }
    ) {
        markers.forEach { marker ->
            Marker(
                state = MarkerState(position = LatLng(marker.lat.toDouble(), marker.lng.toDouble())),
                title = marker.title,
                snippet = marker.snipet
            )
        }
    }
}
