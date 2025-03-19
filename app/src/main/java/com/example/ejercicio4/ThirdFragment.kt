package com.example.ejercicio4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.ejercicio4.databinding.FragmentFirstBinding
import com.example.ejercicio4.databinding.FragmentThirdBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        var edadUsuario = (activity as MainActivity).usuario?.edad
        var listaChecks:MutableList<CheckBox> = mutableListOf()

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
// Add menu items here
                menuInflater.inflate(R.menu.menu_third_fragment, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
// Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_comprar_vehiculos-> {
                        for ((indice, elemento) in listaChecks.withIndex()){
                            if (elemento.isChecked){
                                (activity as MainActivity).usuario?.vehiculosComprados?.add((activity as MainActivity).vehiculos[indice])
                            }
                        }
                        findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
                        true
                    }

                    else-> false
                }
            }
        },viewLifecycleOwner, Lifecycle.State.RESUMED)


        for ((indice,elemento) in(activity as MainActivity).vehiculos.withIndex()){
            if (edadUsuario != null) {
                if (edadUsuario >= 18) {
                    val checkbox = CheckBox(context)
                    checkbox.text = elemento.modelo
                    checkbox.id = indice
                    binding.tfllVehiculos.addView(checkbox)
                    listaChecks.add(checkbox)
                } else {
                    if (elemento.tipo != "coche") {
                        val checkbox = CheckBox(context)
                        checkbox.text = elemento.modelo
                        checkbox.id = indice
                        binding.tfllVehiculos.addView(checkbox)
                        listaChecks.add(checkbox)
                    }
                }
            }
        }

        /*
        binding.tfbComprar.setOnClickListener{
            binding.tflVehiculos.forEach { control ->
                if (control is CheckBox){
                    if (control.isChecked){
                        vehiculosComprados += "${control.text}\n"
                        (activity as MainActivity).usuario?.vehiculos?.add
       }
         */

        binding.tfbComprar.setOnClickListener{
            for ((indice, elemento) in listaChecks.withIndex()){
                if (elemento.isChecked){
                    (activity as MainActivity).usuario?.vehiculosComprados?.add((activity as MainActivity).vehiculos[indice])
                }
            }
            findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}