package com.example.mapsapp.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from

class MySupabaseClient {
    lateinit var client: SupabaseClient

    constructor(supabaseUrl: String, supabaseKey: String){
        client = createSupabaseClient(
            supabaseUrl = supabaseUrl,
            supabaseKey = supabaseKey
        ) {
            install(Auth){autoLoadFromStorage = true}
            install(Postgrest)
        }
    }
    suspend fun getAllMarkers(): List<Marker> {
        return client.from("Marker").select().decodeList<Marker>()
    }

    suspend fun getMarker(id: String): Marker{
        return client.from("Marker").select {
            filter {
                eq("id", id)
            }
        }.decodeSingle<Marker>()
    }

    suspend fun insertMarker(marker: Marker){
        client.from("Marker").insert(marker)
    }
    suspend fun updateMarker(id: String, lat: String, lng: String, title: String, snipet: String){
        client.from("Marker").update({
            set("lat", lat)
            set("lng", lng)
            set("title", title)
            set("snipet", snipet)
        }) { filter { eq("id", id) } }
    }
    suspend fun deleteMarker(id: String){
        client.from("Marker").delete{ filter { eq("id", id) } }
    }



}