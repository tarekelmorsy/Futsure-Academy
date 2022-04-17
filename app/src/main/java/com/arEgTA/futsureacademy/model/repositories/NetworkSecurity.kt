package com.arEgTA.futsureacademy.model.repositories

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class NetworkSecurity : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}