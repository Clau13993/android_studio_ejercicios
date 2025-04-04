package com.example.ejercicio4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.ejercicio4.databinding.ActivityMainBinding
import com.example.ejercicio4.modelo.Usuario
import com.example.ejercicio4.modelo.VM
import com.example.ejercicio4.modelo.Vehiculo

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    val miVM: VM by viewModels()

    lateinit var datos:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        datos = this.getSharedPreferences("datos", Context.MODE_PRIVATE)

        insertarVehiculos()
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    fun insertarVehiculos(){
        miVM.vehiculos.add(Vehiculo("moto","moto1",1250.00))
        miVM.vehiculos.add(Vehiculo("moto","moto2",980.00))
        miVM.vehiculos.add(Vehiculo("moto","moto3",880.00))
        miVM.vehiculos.add(Vehiculo("coche","coche1",2500.00))
        miVM.vehiculos.add(Vehiculo("coche","coche2",9800.00))
        miVM.vehiculos.add(Vehiculo("coche","coche3",15000.00))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}