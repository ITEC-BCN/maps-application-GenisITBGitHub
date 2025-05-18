package com.example.mapsapp

import android.app.Application
import com.example.mapsapp.data.MySupabaseClient

class MyApp: Application() {

    companion object {
        lateinit var database: MySupabaseClient
    }
    override fun onCreate() {
        super.onCreate()
        database = MySupabaseClient(
            supabaseUrl = "https://tejsuqofxrduffosrfth.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRlanN1cW9meHJkdWZmb3NyZnRoIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NzIzNDY2NCwiZXhwIjoyMDYyODEwNjY0fQ.X63SAcGafneMY9zjZoE7DGZhht2vzglL74SX5PM0xMs"
        )
    }

}