package com.example.variosactivitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.variosactivitys.databinding.ActivityVentana2Binding
import modelo.Persona
import modelo.Personas
import android.content.Intent
import android.app.Activity


class Ventana2 : AppCompatActivity() {
    lateinit var binding: ActivityVentana2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ACSCO", "ONCREATE(), Ventana 2")
        binding = ActivityVentana2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_ventana2)

        /*var nombre = intent.getStringExtra("nombre")
        var edad = intent.getStringExtra("edad")
        var persona:Persona = Persona(nombre,edad)*/
        var p : Persona = intent.getSerializableExtra("obj") as Persona
        binding.cajaNombre.setText(p.nombre)
        binding.cajaEdad.setText(p.edad)


        Personas.aniadirPersona(p)
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

        //Devolver datos a la ventana 1 de forma deprecated.
        binding.btDevolver.setOnClickListener {
            // Get the text from the EditText
            val stringToPassBack = binding.cajaDevolver.text.toString()

            // Put the String to pass back into an Intent and close this activity
            val intent = Intent()
            intent.putExtra("keyName", stringToPassBack)
            setResult(Activity.RESULT_OK, intent)//si cancelase o no rellena, se pasa RESULT_CANCELED
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