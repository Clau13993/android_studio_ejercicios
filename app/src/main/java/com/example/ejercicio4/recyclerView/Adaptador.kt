package com.example.ejercicio4.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio4.R
import com.example.ejercicio4.databinding.RecyclerViewItemBinding
import com.example.ejercicio4.modelo.Vehiculo

class Adaptador(private val lista:MutableList<Vehiculo>): RecyclerView.Adapter<Adaptador.VehiculoVH>(){

    inner class VehiculoVH (val binding:RecyclerViewItemBinding): RecyclerView.ViewHolder(binding.root){
        var posicion:Int = 0
        init{
            binding.rvibCompra.setOnClickListener{
                val miBundle = bundleOf("posicion" to posicion)
                binding.rviClPrincipal.findNavController().navigate(R.id.action_thirdFragment_to_fourthFragment, miBundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoVH {
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehiculoVH(binding)
    }

    override fun getItemCount(): Int {
        return lista.count()
    }

    override fun onBindViewHolder(holder: VehiculoVH, position: Int) {
        holder.binding.rviNombre.text=lista[position].modelo
        holder.posicion = position
    }
}