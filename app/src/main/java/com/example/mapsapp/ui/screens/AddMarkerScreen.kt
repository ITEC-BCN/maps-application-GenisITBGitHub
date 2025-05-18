package com.example.mapsapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapsapp.viewmodels.MyViewModel

@Composable
fun AddMarkerScreen(navigateBack:()-> Unit) {
    val myViewModel = viewModel<MyViewModel>()

    val markerlat: String by myViewModel.markerlat.observeAsState("")
    val markerlng: String by myViewModel.markerlng.observeAsState("")
    val markertitle: String by myViewModel.markertitle.observeAsState("")
    val markersnipet: String by myViewModel.markersnipet.observeAsState("")
    Column(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth().weight(0.4f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text("Crea un nou marcador", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            TextField(value = markerlat, onValueChange = { myViewModel.editMarkerlat(it)  }, label = { Text("Latitud")})
            TextField(value = markerlng, onValueChange = { myViewModel.editMarkerlng(it) }, label = { Text("Longitud")})
            TextField(value = markertitle, onValueChange = { myViewModel.editMarkertitle(it) }, label = { Text("Títol")})
            TextField(value = markersnipet, onValueChange = { myViewModel.editMarkersnipet(it) }, label = { Text("Snipet")})
            Button(onClick = {  myViewModel.insertNewMarker(markerlat,markerlng,markertitle,markersnipet)
            navigateBack()}) {
                Text("Insert")
            }
        }
    }

}