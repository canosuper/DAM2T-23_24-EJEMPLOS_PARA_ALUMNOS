package com.example.variosactivitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.variosactivitys.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ACSCO", "ONCREATE(), Ventana 1")
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.boton.setOnClickListener {
            irAVentana2()
        }

    }

    private fun irAVentana2() {
        //podríamos crear aqui el objeto y pasarlo, en vez de los atributos individualmente.
        // Persona debería ser serializable en ese caso.
        var miIntent: Intent = Intent(this, Ventana2::class.java)
        miIntent.putExtra("nombre", binding.cajaNombre.text.toString())
        miIntent.putExtra("edad", binding.cajaEdad.text.toString())
        startActivity(miIntent)
    }

    override fun onStart() {
        super.onStart()
        Log.e("ACSCO", "ONSTART(), Ventana 1")
    }

    override fun onResume() {
        super.onResume()
        Log.e("ACSCO", "ONRESUME(), Ventana 1")
    }
    override fun onPause(){
        super.onPause()
        Log.e("ACSCO", "ONPAUSE(), Ventana 1")
    }
    override fun onStop() {
        super.onStop()
        Log.e("ACSCO", "ONSTOP(), Ventana 1")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e("ACSCO", "ONDESTROY(), Ventana 1")
    }
}