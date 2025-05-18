package com.example.mapsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.mapsapp.ui.navigation.Destination.AddMarker_route
import com.example.mapsapp.ui.navigation.Destination.Map_route
import com.example.mapsapp.ui.navigation.Destination.MarkerList_route
import com.example.mapsapp.ui.navigation.Destination.Marker_detail_route
import com.example.mapsapp.ui.screens.AddMarkerScreen
import com.example.mapsapp.ui.screens.MapScreen
import com.example.mapsapp.ui.screens.MarkerDetailScreen
import com.example.mapsapp.ui.screens.MarkerListScreen

@Composable
fun NavigationWrapper(navController: NavHostController, padding: Modifier) {
    NavHost(navController, Map_route){
        composable<Map_route> {
            MapScreen()
        }
        composable<MarkerList_route> {
            MarkerListScreen(
                onNavigateToAddMarker = {
                    navController.navigate(AddMarker_route)
                },
                onNavigateToMarkerDetail = { id, lat, lng, title, snipet ->
                    navController.navigate(
                        Marker_detail_route(id, lat, lng, title, snipet)
                    )
                }
            )
        }

        composable<AddMarker_route> {
            AddMarkerScreen(){ navController.navigate(MarkerList_route){
                popUpTo(Map_route) { inclusive = true }
            } }
        }
        composable<Marker_detail_route> { backStackEntry ->
            val marker = backStackEntry.toRoute<Marker_detail_route>()
            MarkerDetailScreen(
                id = marker.id,
                lat = marker.lat,
                lng = marker.lng,
                title = marker.title,
                snipet = marker.snipet
            ){
                navController.navigate(MarkerList_route){
                    popUpTo(Map_route) { inclusive = true }
                }
            }
        }
    }
}