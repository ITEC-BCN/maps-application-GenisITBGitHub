package com.example.mapsapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home

import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class DrawerItem(
    val icon: ImageVector,
    val text: String,
    val route: Destination
) {
    MAP(Icons.Default.Home, "Map", Destination.Map_route),
    MARKERLIST(Icons.Default.Settings, "Marker List", Destination.MarkerList_route),
}
