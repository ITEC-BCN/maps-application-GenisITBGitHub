package com.example.mapsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapsapp.MyApp
import com.example.mapsapp.data.Marker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel: ViewModel() {
//    private val _markerList = MutableLiveData<MutableList<Marker>>()
//    val markers = _markerList
    val database = MyApp.database
    private val _markerlat = MutableLiveData<String>()
    val markerlat = _markerlat
    private val _markerlng = MutableLiveData<String>()
    val markerlng = _markerlng
    private val _markertitle = MutableLiveData<String>()
    val markertitle = _markertitle
    private val _markersnipet = MutableLiveData<String>()
    val markersnipet = _markersnipet

    fun editMarkerlat(newtext: String) {
        _markerlat.value = newtext
    }
    fun editMarkerlng(newtext: String) {
        _markerlng.value = newtext
    }
    fun editMarkertitle(newtext: String) {
        _markertitle.value = newtext
    }
    fun editMarkersnipet(newtext: String) {
        _markersnipet.value = newtext
    }



    fun insertNewMarker(lat: String, lng:String, title:String, snipet:String) {
        val newMarker = Marker(lat= lat, lng = lng, title = title, snipet = snipet)
        CoroutineScope(Dispatchers.IO).launch {
            database.insertMarker(newMarker)
            database.getAllMarkers()
        }
    }

    private val _markersList = MutableLiveData<List<Marker>>()
    val markersList = _markersList

    fun getAllMarkers() {
        CoroutineScope(Dispatchers.IO).launch {
            val databaseMarkers = database.getAllMarkers()
            withContext(Dispatchers.Main) {
                _markersList.value = databaseMarkers
            }
        }
    }

    private var _selectedMarker: Marker? = null
    fun updateMarker( id: Int?,lat:String, lng: String, title: String, snipet: String){
        CoroutineScope(Dispatchers.IO).launch {
            database.updateMarker(id.toString(), lat, lng, title, snipet )
        }
    }
    fun getMarker(id: String){
        if(_selectedMarker == null){
            CoroutineScope(Dispatchers.IO).launch {
                val marker = database.getMarker(id)
                withContext(Dispatchers.Main) {
                    _selectedMarker = marker
                    _markerlat.value = marker.lat
                    _markerlng.value = marker.lng
                    _markertitle.value = marker.title
                    _markersnipet.value = marker.snipet
                }
            }
        }
    }

    fun deleteMarker(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            database.deleteMarker(id)
            getAllMarkers()
        }
    }



}