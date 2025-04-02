package com.example.ejercicio4.modelo

import android.content.SharedPreferences
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel

class VM: ViewModel() {
    var usuario: Usuario?=null
    var vehiculos: MutableList<Vehiculo> = mutableListOf()

}