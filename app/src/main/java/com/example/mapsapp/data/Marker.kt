package com.example.mapsapp.data

import kotlinx.serialization.Serializable


@Serializable
data class Marker (
    val id: Int? = null,
    var lat: String,
    var lng: String,
    var title: String,
    var snipet:String
)