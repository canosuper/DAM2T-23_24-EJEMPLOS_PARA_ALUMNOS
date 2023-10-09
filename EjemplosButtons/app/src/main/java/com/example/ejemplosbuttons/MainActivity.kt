package com.example.ejemplosbuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var edTexto : EditText
    lateinit var btnPulsame : Button
    lateinit var chkLicencia: CheckBox
    lateinit var rb1 : RadioButton
    lateinit var rb2 : RadioButton
    lateinit var rb3 : RadioButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edTexto=findViewById(R.id.edTexto)
        btnPulsame=findViewById(R.id.btnPulsame)
        chkLicencia=findViewById(R.id.chkLicencia)
        rb1=findViewById(R.id.rb1)
        rb2=findViewById(R.id.rb22)
        rb3=findViewById(R.id.rb33)

        btnPulsame.setOnClickListener {
            if(chkLicencia.isChecked){
                edTexto.setText("LICENCIA ACEPTADA")
            }
            else{
                edTexto.setText("LICENCIA SIN ACEPTAR")
            }

            if(rb1.isChecked){
                Toast.makeText(this, "Opción 1 pulsada", Toast.LENGTH_SHORT).show()
            }
            else{
                if(rb2.isChecked){
                    Toast.makeText(this, "Opción 2 pulsada", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Opción 3 pulsada", Toast.LENGTH_SHORT).show()
                }
            }



        }

        chkLicencia.setOnClickListener {
            if(chkLicencia.isChecked){
                chkLicencia.setText("LICENCIA ACEPTADA")
            }
            else{
                chkLicencia.setText("LICENCIA SIN ACEPTAR")
            }
        }
    }
}