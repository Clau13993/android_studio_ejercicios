package com.example.ejercicio4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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
        for ((indice,elemento) in(activity as MainActivity).vehiculos.withIndex()){
            if (edadUsuario != null) {
                if(edadUsuario >= 18 ){
                    val checkbox = CheckBox(context)
                    checkbox.text = elemento.modelo
                    checkbox.id = indice
                    binding.tfllVehiculos.addView(checkbox)
                }else{
                    val checkbox = CheckBox(context)
                    if(elemento.tipo != "coche")
                        checkbox.text = elemento.modelo
                        checkbox.id = indice
                        binding.tfllVehiculos.addView(checkbox)
                        listaChecks.add(checkbox)
                }
            }

        }

        binding.tfbComprar.setOnClickListener{

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}