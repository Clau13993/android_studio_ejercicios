package com.example.ejercicio4.modelo

class Usuario (var nombre:String, val apellido:String, val edad:Int){
    var vehiculosComprados: MutableList<Vehiculo> = mutableListOf()
}