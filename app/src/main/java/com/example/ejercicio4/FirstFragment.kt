package com.example.ejercicio4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.ejercicio4.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        var nombreUsuario = (activity as MainActivity).usuario?.nombre
        var apellidoUsuario = (activity as MainActivity).usuario?.apellido
        var listaVehiculosComprados = (activity as MainActivity).usuario?.vehiculosComprados

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
// Add menu items here
                menuInflater.inflate(R.menu.menu_first_fragment, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
// Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_insertar_datos-> {
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                        true
                    }
                    R.id.action_comprar->{
                        if (nombreUsuario != null) {
                            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
                        }else{
                            val toastCompletar = Toast.makeText(activity,"Rellena primero el formulario", Toast.LENGTH_LONG)
                            toastCompletar.show()
                        }
                        true
                    }
                    else-> false
                }
            }
        },viewLifecycleOwner, Lifecycle.State.RESUMED)


        if (nombreUsuario !=null && apellidoUsuario != null){
            binding.fftvDatos.text = "Bienvenid@ $nombreUsuario $apellidoUsuario!"
        }else{
            binding.fftvDatos.text = "Bienvenid@!"
        }


        if (listaVehiculosComprados != null) {
            for (elemento in listaVehiculosComprados) {
                val nuevaCompra = TextView(context)
                nuevaCompra.text = elemento.modelo + " || Precio: " + elemento.precio
                binding.lldatosVehiculosComprados.addView(nuevaCompra)
            }
        }

        binding.ffbDatos.setOnClickListener{
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                }


        binding.ffbComprar.setOnClickListener{
            if (nombreUsuario != null) {
                findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
            }else{
                val toastCompletar = Toast.makeText(activity,"Rellena primero el formulario", Toast.LENGTH_LONG)
                toastCompletar.show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}