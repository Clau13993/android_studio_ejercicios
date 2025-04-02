package com.example.ejercicio4


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.ejercicio4.databinding.FragmentSecondBinding
import com.example.ejercicio4.modelo.Usuario

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
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
// Add menu items here
                menuInflater.inflate(R.menu.menu_second_fragment, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
// Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_insertar-> {
                        if (validarDatos(binding.sfetNombre.text.toString(),binding.sfetApellidos.text.toString(),binding.sfetEdad.text.toString().toInt())){
                            val usuario = Usuario(binding.sfetNombre.text.toString(),binding.sfetApellidos.text.toString(),binding.sfetEdad.text.toString().toInt())
                            (activity as MainActivity).miVM.usuario = usuario
                            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)

                        }
                        true
                    }

                    else-> false
                }
            }
        },viewLifecycleOwner, Lifecycle.State.RESUMED)


        binding.sfbVolver.setOnClickListener{
            if (validarDatos(binding.sfetNombre.text.toString(),binding.sfetApellidos.text.toString(),binding.sfetEdad.text.toString().toInt())){
                val usuario = Usuario(binding.sfetNombre.text.toString(),binding.sfetApellidos.text.toString(),binding.sfetEdad.text.toString().toInt())
                (activity as MainActivity).miVM.usuario = usuario
                findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
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