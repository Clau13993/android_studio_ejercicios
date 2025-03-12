package com.example.ejercicio4


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ejercicio4.databinding.FragmentSecondBinding
import com.example.ejercicio4.modelo.Usuario

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sfbVolver.setOnClickListener{
            if (validarDatos(binding.sfetNombre.text.toString(),binding.sfetApellidos.text.toString(),binding.sfetEdad.text.toString().toInt())){
                val usuario = Usuario(binding.sfetNombre.text.toString(),binding.sfetApellidos.text.toString(),binding.sfetEdad.text.toString().toInt())
                (activity as MainActivity).usuario = usuario
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun validarDatos(nombre:String, apellido:String, edad:Int):Boolean{
        var datosBien = true
        val toastCompletar = Toast.makeText(activity,"Faltan campos por completar",Toast.LENGTH_LONG)
        val toastEdad = Toast.makeText(activity,"La edad debe ser entre 16 - 80 años",Toast.LENGTH_LONG)

        if (nombre.isEmpty() || apellido.isEmpty()){
            toastCompletar.show()
            datosBien = false
        }

        if (edad < 16 || edad > 80){
            toastEdad.show()
            datosBien = false
        }

        return datosBien
    }
}