package com.example.fieldwise

import android.app.Application
import androidx.room.Room
import com.example.fieldwise.data.AppDatabase

class App : Application() {


    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "user-database"
        ).build()
    }


    val userProfileDao by lazy {
        db.userProfileDao()
    }
}