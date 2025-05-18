package com.example.mapsapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mapsapp.data.Marker
import com.example.mapsapp.viewmodels.MyViewModel

@Composable
fun MarkerListScreen(onNavigateToMarkerDetail:(Int?, String, String, String,String)-> Unit,
                     onNavigateToAddMarker: () -> Unit,) {
    val myViewModel = viewModel<MyViewModel>()

    val markersList by myViewModel.markersList.observeAsState(emptyList<Marker>())
    myViewModel.getAllMarkers()
    ConstraintLayout(
        Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)
            .fillMaxSize()
    ){
        LazyColumn(Modifier

            .fillMaxWidth()
            .fillMaxHeight()) {
            items(markersList.size) { index ->
                MarkerItem(marker = markersList[index], onNavigateToMarkerDetail)
            }
        }

        val button = createRef()
        Button(
            onClick = {onNavigateToAddMarker()},
            modifier = Modifier
                .size(60.dp)
                .constrainAs(button) {
                    end.linkTo(parent.end, margin = 20.dp)
                    bottom.linkTo(parent.bottom, margin = 100.dp)
                },
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(text = "+", fontSize = 30.sp, textAlign = TextAlign.Center)
        }
    }



}

@Composable
fun MarkerItem(marker:Marker, onNavigateToMarkerDetail: (Int?, String, String, String,String) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Black),
        onClick =  { onNavigateToMarkerDetail(marker.id,marker.lng,marker.lat, marker.title, marker.snipet) },
        modifier = Modifier
            .height(50.dp)
            .clickable {

        }
            .padding(8.dp),
        ) {
        Row(Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(marker.title, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
    }
}
