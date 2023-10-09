package com.example.calculadorabasica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var btnSumar:Button
    lateinit var btnRestar:Button
    lateinit var btnProducto:Button
    lateinit var btnDivision:Button
    lateinit var edResultado:EditText
    lateinit var edNum1:EditText
    lateinit var edNum2:EditText
    lateinit var edResto:EditText
    lateinit var txtResto:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        //comentario solo para el commit
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSumar = findViewById(R.id.btnSumar)
        btnRestar = findViewById(R.id.btnRestar)
        btnProducto = findViewById(R.id.btnProducto)
        btnDivision = findViewById(R.id.btnDivisionEntera)
        edResultado = findViewById(R.id.edResultado)
        edResto = findViewById(R.id.edResto)
        edNum1 = findViewById(R.id.edNum1)
        edNum2 = findViewById(R.id.edNum2)
        txtResto = findViewById(R.id.txtResto)

        btnSumar.setOnClickListener {
            if ((edNum1.text.isNotEmpty() && (edNum2.text.isNotEmpty()))) {
                edResultado.setText(
                    (edNum1.text.toString().toInt() + edNum2.text.toString().toInt()).toString()
                )
                txtResto.visibility = View.INVISIBLE
                edResto.visibility = View.INVISIBLE
            } else {
                Toast.makeText(this, "Los dígitos no pueden quedar vacíos", Toast.LENGTH_SHORT)
                    .show()
            }


        }
        btnRestar.setOnClickListener {
            if ((edNum1.text.isNotEmpty() && (edNum2.text.isNotEmpty()))) {
                edResultado.setText(
                    (edNum1.text.toString().toInt() - edNum2.text.toString().toInt()).toString()
                )
                txtResto.visibility = View.INVISIBLE
                edResto.visibility = View.INVISIBLE
            } else {
                Toast.makeText(this, "Los dígitos no pueden quedar vacíos", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        btnProducto.setOnClickListener {
            if ((edNum1.text.isNotEmpty() && (edNum2.text.isNotEmpty()))) {
                edResultado.setText(
                    (edNum1.text.toString().toInt() * edNum2.text.toString().toInt()).toString()
                )
                txtResto.visibility = View.INVISIBLE
                edResto.visibility = android.view.View.INVISIBLE
            } else {
                Toast.makeText(this, "Los dígitos no pueden quedar vacíos", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        btnDivision.setOnClickListener {
            if ((edNum1.text.isNotEmpty() && (edNum2.text.isNotEmpty()))) {
                if (edNum2.text.toString().toInt() != 0) {
                    Log.d("ACSCO", "Entro en el If de la division")
                    edResultado.setText(
                        (edNum1.text.toString().toInt() / edNum2.text.toString().toInt()).toString()
                    )
                    edResto.setText(
                        (edNum1.text.toString().toInt() % edNum2.text.toString().toInt()).toString()
                    )
                    edResto.visibility = View.VISIBLE
                    txtResto.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this, "El número 2, no puede ser 0 ", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Los dígitos no pueden quedar vacíos\"", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}