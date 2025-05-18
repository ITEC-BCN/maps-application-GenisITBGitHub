package com.example.mapsapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapsapp.viewmodels.MyViewModel

@Composable
fun MarkerDetailScreen(id: Int?, lat: String, lng: String, title: String, snipet: String, navigateBack:()-> Unit) {
    val myViewModel = viewModel<MyViewModel>()
    myViewModel.getMarker(id.toString())
    val markerlat: String by myViewModel.markerlat.observeAsState("")
    val markerlng: String by myViewModel.markerlng.observeAsState("")
    val markertitle: String by myViewModel.markertitle.observeAsState("")
    val markersnipet: String by myViewModel.markersnipet.observeAsState("")

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        TextField(value = markerlat, onValueChange = { myViewModel.editMarkerlat(it) })
        TextField(value = markerlng, onValueChange = { myViewModel.editMarkerlng(it) })
        TextField(value = markertitle, onValueChange = { myViewModel.editMarkertitle(it) })
        TextField(value = markersnipet, onValueChange = { myViewModel.editMarkersnipet(it) })
        Button(onClick = {
            myViewModel.updateMarker(id,markerlat,markerlng,markertitle,markersnipet)
            navigateBack()
            }) {
            Text("Update")
        }
        Button(onClick = {
            myViewModel.deleteMarker(id = id.toString())
            navigateBack()
            },) {
            Text(text = "Delete")
        }
    }

}