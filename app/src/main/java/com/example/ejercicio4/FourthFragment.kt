package com.example.ejercicio4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio4.databinding.FragmentFourthBinding
import com.example.ejercicio4.databinding.FragmentThirdBinding
import com.example.ejercicio4.modelo.Vehiculo
import com.example.ejercicio4.recyclerView.Adaptador

class FourthFragment : Fragment() {

    private var _binding: FragmentFourthBinding? = null

    private var posicion:Int = -1
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        posicion=arguments?.getInt("posicion") ?:-1
        var vehiculo:Vehiculo
        if (posicion == -1){
            findNavController().popBackStack()
        }else{
            vehiculo = (activity as MainActivity).miVM.vehiculos[posicion]
            binding.tipoVehiculo.setText(vehiculo.tipo)
            binding.modeloVehiculo.setText(vehiculo.modelo)
            binding.precioVehiculo.setText(vehiculo.precio.toString())
        }

        binding.bComprar.setOnClickListener{
            comprar()
        }

    }

    fun comprar(){
        (activity as MainActivity).miVM.usuario?.vehiculosComprados?.add((activity as MainActivity).miVM.vehiculos[posicion])
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}