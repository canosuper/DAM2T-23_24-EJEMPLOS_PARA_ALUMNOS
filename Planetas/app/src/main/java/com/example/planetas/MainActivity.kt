package com.example.planetas

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class MainActivity : AppCompatActivity() {
    lateinit var btnTierra: Button
    lateinit var btnMercu: Button
    lateinit var btnVenus: Button
    lateinit var btnMarte: Button
    lateinit var btnJupit: Button
    lateinit var btnSatur: Button
    lateinit var btnUrano: Button
    lateinit var btnNeptu: Button
    lateinit var edNombre:EditText
    lateinit var edTipo:EditText
    lateinit var edRadio:EditText
    lateinit var edGravedad:EditText
    lateinit var edMasa:EditText
    lateinit var edDistancia:EditText
    lateinit var rb1:RadioButton
    lateinit var rb2:RadioButton
    lateinit var rb3:RadioButton
    
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTierra=findViewById(R.id.btnTierra)
        btnMercu=findViewById(R.id.btnMercurio)
        btnVenus=findViewById(R.id.btnVenus)
        btnMarte=findViewById(R.id.btnMarte)
        btnJupit=findViewById(R.id.btnJupit)
        btnSatur=findViewById(R.id.btnSatur)
        btnUrano=findViewById(R.id.btnUrano)
        btnNeptu=findViewById(R.id.btnNeptu)

        edNombre=findViewById(R.id.edNombre)
        edTipo=findViewById(R.id.edTipo)
        edRadio=findViewById(R.id.edRadio)
        edGravedad=findViewById(R.id.edGravedad)
        edMasa=findViewById(R.id.edMasa)
        edDistancia=findViewById(R.id.edDistancia)

        rb1=findViewById(R.id.rb1)
        rb2=findViewById(R.id.rb2)
        rb3=findViewById(R.id.rb3)

        btnMercu.setOnClickListener{
           detallesPlaneta(0)
        }
        btnVenus.setOnClickListener{
            detallesPlaneta(1)
        }
        btnTierra.setOnClickListener{
            detallesPlaneta(2)
        }
        btnMarte.setOnClickListener{
            detallesPlaneta(3)
        }
        btnJupit.setOnClickListener{
            detallesPlaneta(4)
        }
        btnSatur.setOnClickListener{
            detallesPlaneta(5)
        }
        btnUrano.setOnClickListener{
            detallesPlaneta(6)
        }
        btnNeptu.setOnClickListener{
            detallesPlaneta(7)
        }

        rb1.setOnClickListener {
            btnMercu.visibility = View.VISIBLE
            btnVenus.visibility = View.VISIBLE
            btnTierra.visibility = View.VISIBLE
            btnMarte.visibility = View.VISIBLE
            btnJupit.visibility = View.INVISIBLE
            btnSatur.visibility = View.INVISIBLE
            btnNeptu.visibility = View.INVISIBLE
            btnUrano.visibility = View.INVISIBLE

        }
        rb2.setOnClickListener {
            btnMercu.visibility = View.INVISIBLE
            btnVenus.visibility = View.INVISIBLE
            btnTierra.visibility = View.INVISIBLE
            btnMarte.visibility = View.INVISIBLE
            btnJupit.visibility = View.VISIBLE
            btnSatur.visibility = View.VISIBLE
            btnNeptu.visibility = View.INVISIBLE
            btnUrano.visibility = View.INVISIBLE

        }
        rb3.setOnClickListener {
            btnMercu.visibility = View.INVISIBLE
            btnVenus.visibility = View.INVISIBLE
            btnTierra.visibility = View.INVISIBLE
            btnMarte.visibility = View.INVISIBLE
            btnJupit.visibility = View.INVISIBLE
            btnSatur.visibility = View.INVISIBLE
            btnNeptu.visibility = View.VISIBLE
            btnUrano.visibility = View. VISIBLE

        }

    }

    private fun detallesPlaneta(i:Int) {

        edNombre.setText(Planetas.planetas[i].nombre)
        edTipo.setText(Planetas.planetas[i].tipo.toString())
        edRadio.setText(Planetas.planetas[i].radio.toString()+" kms.")
        edGravedad.setText(Planetas.planetas[i].gravedad.toString()+" m/s2.")
        edMasa.setText(Planetas.planetas[i].masa.toString()+" kg.")
        edDistancia.setText(Planetas.planetas[i].distanciaAlSol.toString()+" mill de kms.")
    }


}


