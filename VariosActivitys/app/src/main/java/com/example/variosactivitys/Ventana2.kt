package com.example.variosactivitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.variosactivitys.databinding.ActivityVentana2Binding
import modelo.Persona
import modelo.Personas

class Ventana2 : AppCompatActivity() {
    lateinit var binding: ActivityVentana2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ACSCO", "ONCREATE(), Ventana 2")
        binding = ActivityVentana2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_ventana2)

        var nombre = intent.getStringExtra("nombre")
        var edad = intent.getStringExtra("edad")
        var persona:Persona = Persona(nombre,edad)
        binding.cajaNombre.setText(nombre)
        binding.cajaEdad.setText(edad)


        Personas.aniadirPersona(persona)
        var cadena: String = ""
        var i:Int=1
        for(p in Personas.personas){
            cadena+=" "+i+". " +p.nombre+" "+p.edad +"\n"
            i++
            binding.multiLine.setText(cadena)
        }
        binding.boton.setOnClickListener {
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
        Log.e("ACSCO", "ONSTART(), Ventana 2")
    }

    override fun onResume() {
        super.onResume()
        Log.e("ACSCO", "ONRESUME(), Ventana 2")
    }
    override fun onPause(){
        super.onPause()
        Log.e("ACSCO", "ONPAUSE(), Ventana 2")
    }
    override fun onStop() {
        super.onStop()
        Log.e("ACSCO", "ONSTOP(), Ventana 2")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e("ACSCO", "ONDESTROY(), Ventana 2")
    }
}