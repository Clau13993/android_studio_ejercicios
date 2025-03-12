package com.example.ejercicio1

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        findViewById<Button>(R.id.btnComprar).setOnClickListener{
            var precioCocaCola = 0.57
            var precioKasLimon = 0.54
            var precioKasNaranja = 0.54
            var precioRedBull = 1.25
            var precioCerveza = 0.62
            var precioVino = 3.21

            var totalPagar = 0.0


            var cantidadCocacola = 0
            if (findViewById<EditText>(R.id.cantidadCocaCola).text.isNotEmpty()){
                cantidadCocacola = findViewById<EditText>(R.id.cantidadCocaCola).text.toString().toInt()
            }

            var cantidadKasLimon = 0
            if (findViewById<EditText>(R.id.cantidadLimon).text.isNotEmpty()){
                cantidadKasLimon = findViewById<EditText>(R.id.cantidadCocaCola).text.toString().toInt()
            }

            var cantidadKasNaranja = 0
            if (findViewById<EditText>(R.id.cantidadLimon).text.isNotEmpty()){
                cantidadKasNaranja = findViewById<EditText>(R.id.cantidadCocaCola).text.toString().toInt()
            }

            var cantidadRedBull = 0
            if (findViewById<EditText>(R.id.cantidadLimon).text.isNotEmpty()){
                cantidadRedBull = findViewById<EditText>(R.id.cantidadCocaCola).text.toString().toInt()
            }

            var cantidaCerveza = 0
            if (findViewById<EditText>(R.id.cantidadLimon).text.isNotEmpty()){
                cantidaCerveza = findViewById<EditText>(R.id.cantidadCocaCola).text.toString().toInt()
            }

            var cantidadVino = 0
            if (findViewById<EditText>(R.id.cantidadLimon).text.isNotEmpty()){
                cantidadVino = findViewById<EditText>(R.id.cantidadCocaCola).text.toString().toInt()
            }

            totalPagar = (cantidadCocacola * precioCocaCola) + (cantidadKasLimon * precioKasLimon) + (cantidadKasNaranja * precioKasNaranja) +
                    (cantidadRedBull * precioRedBull) + (cantidaCerveza * precioCerveza) + (cantidadVino * precioVino)

            if (validar()){
                if (cantidadVino > 0 || cantidaCerveza > 0){
                    if(mayorEdad()){
                        findViewById<TextView>(R.id.resultado).setText("Total a pagar: $totalPagar")
                        totalPagar = 0.0
                    }else{
                        findViewById<TextView>(R.id.resultado).setText("Tienes que ser mayor de edad para comprar alcohol")
                    }
                }else{
                    findViewById<TextView>(R.id.resultado).setText("Total a pagar: $totalPagar")
                    totalPagar = 0.0
                }
            }
        }
    }

    fun validar(): Boolean {
        if(findViewById<TextView>(R.id.nombreYapellidos).text.isEmpty()){
            findViewById<TextView>(R.id.resultado).setText("Tienes que insertar nombre y apellidos")
            return false
        }else{
            return true
        }
    }

    fun mayorEdad(): Boolean{
        if(findViewById<CheckBox>(R.id.checkBox).isChecked){
            return true
        }else{
            return false
        }
    }
}