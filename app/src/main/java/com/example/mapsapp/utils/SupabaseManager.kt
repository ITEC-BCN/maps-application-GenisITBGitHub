@file:OptIn(SupabaseInternal::class)

package com.example.mapsapp.utils



import io.github.jan.supabase.BuildConfig
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.admin.AdminUserBuilder.Email

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient

class SupabaseManager {
    private val supabaseUrl = "https://tejsuqofxrduffosrfth.supabase.co"
    private val supabaseKey =  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRlanN1cW9meHJkdWZmb3NyZnRoIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0NzIzNDY2NCwiZXhwIjoyMDYyODEwNjY0fQ.X63SAcGafneMY9zjZoE7DGZhht2vzglL74SX5PM0xMs"

    private val supabase = createSupabaseClient(
        supabaseUrl = supabaseUrl,
        supabaseKey = supabaseKey
    ) {
        install(Auth) {
            autoLoadFromStorage = true
        }
    }
    suspend fun signUpWithEmail(emailValue: String, passwordValue: String): AuthState {
        try {

            return AuthState.Authenticated
        } catch (e: Exception) {
            return AuthState.Error(e.localizedMessage)
        }
    }




}
