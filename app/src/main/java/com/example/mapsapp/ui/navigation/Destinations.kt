package com.example.mapsapp.ui.navigation
import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    object Map_route: Destination()

    @Serializable
    object MarkerList_route: Destination()


    @Serializable
    object AddMarker_route: Destination()

    @Serializable
    data class Marker_detail_route(
        val id: Int?, val lat: String,
        val lng: String,
        val title: String, val snipet: String
    )

}
