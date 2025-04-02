package com.example.ejercicio4.modelo

import androidx.activity.viewModels
import androidx.lifecycle.ViewModel

class VM: ViewModel() {
    var usuario: Usuario?=null
    var vehiculos: MutableList<Vehiculo> = mutableListOf()
}