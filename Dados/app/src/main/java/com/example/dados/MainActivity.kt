package com.example.dados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.dados.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var tiradas1: Int = 0
    var tiradas2: Int = 0
    var puntos1: Int = 0
    var puntos2: Int = 0
    var ganadas1: Int = 0
    var ganadas2: Int = 0
    //adaptarlo creando una Clase Jugador, y otra Dado, dentro de una clase JuegoDados
    //en vez de variables sueltas
    //además todas las etiquetas deberían obtenerse el fichero string.
    val dadoImagenes = mapOf(
        0 to R.drawable.num0,
        1 to R.drawable.num1,
        2 to R.drawable.num2,
        3 to R.drawable.num3,
        4 to R.drawable.num4,
        5 to R.drawable.num5,
        6 to R.drawable.num6
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bot1.setOnClickListener {
            // Obtén la imagen correspondiente del Map
            tiradas1++;
            if(tiradas1<=5) {
                val numAux=aleatorio()
                val imagenDado = dadoImagenes[numAux]
                if (imagenDado != null) {
                    binding.dado1.setImageResource(imagenDado)
                    puntos1 += numAux

                    binding.cajaTiradas1.setText(tiradas1.toString())
                    binding.cajaPuntos1.setText(puntos1.toString())
                }
                if((tiradas1>=5)&&(tiradas2>=5)){
                    if(puntos1>puntos2){
                        binding.textView7.text="¡CAMPEÓN!"
                        ganadas1++
                        binding.cajaGanadas1.text=ganadas1.toString()
                    }
                    else{
                        if(puntos1<puntos2){
                            binding.textView8.text="¡CAMPEÓN!"
                            ganadas2++
                            binding.cajaGanadas2.text=ganadas2.toString()
                        }
                        else{
                            binding.textView7.text="¡EMPATE!"
                            binding.textView8.text="¡EMPATE!"
                            ganadas1++
                            ganadas2++
                            binding.cajaGanadas1.text=ganadas1.toString()
                            binding.cajaGanadas2.text=ganadas2.toString()
                        }
                    }
                    binding.botReiniciar.visibility= View.VISIBLE
                    binding.botReiniciarTodo.visibility= View.VISIBLE
                }
            }
            else{
                Toast.makeText(this, "El número de tiradas del jugador 1 no puede ser mayor de 5",Toast.LENGTH_SHORT).show()
            }

        }
        binding.bot2.setOnClickListener {
            tiradas2++;
            if(tiradas2<=5) {
                val numAux=aleatorio()
                val imagenDado = dadoImagenes[numAux]
                if (imagenDado != null) {
                    binding.dado2.setImageResource(imagenDado)
                    puntos2 += numAux

                    binding.cajaTiradas2.setText(tiradas2.toString())
                    binding.cajaPuntos2.setText(puntos2.toString())
                }
                if((tiradas1>=5)&&(tiradas2>=5)){
                    if(puntos1>puntos2){
                        binding.textView7.text="¡CAMPEÓN!"
                        ganadas1++
                        binding.cajaGanadas1.text=ganadas1.toString()
                    }
                    else{
                        if(puntos1<puntos2){
                            binding.textView8.text="¡CAMPEÓN!"
                            ganadas2++
                            binding.cajaGanadas2.text=ganadas2.toString()

                        }
                        else{
                            binding.textView7.text="¡EMPATE!"
                            binding.textView8.text="¡EMPATE!"
                            ganadas1++
                            ganadas2++
                            binding.cajaGanadas1.text=ganadas1.toString()
                            binding.cajaGanadas2.text=ganadas2.toString()
                        }
                    }
                    binding.botReiniciar.visibility= View.VISIBLE
                    binding.botReiniciarTodo.visibility= View.VISIBLE
                }
            }
            else{
                Toast.makeText(this, "El número de tiradas del jugador 2 no puede ser mayor de 5",Toast.LENGTH_SHORT).show()
            }

        }

        binding.botReiniciar.setOnClickListener {
            tiradas1=0
            tiradas2=0
            puntos1=0
            puntos2=0
            val imagenDado = dadoImagenes[0]
            binding.dado1.setImageResource(imagenDado!!)//fuerzo a que no es nulo y ahorro el if
            binding.dado2.setImageResource(imagenDado!!)
            binding.cajaPuntos1.setText("0")
            binding.cajaPuntos2.setText("0")
            binding.cajaTiradas1.setText("0")
            binding.cajaTiradas2.setText("0")
            binding.botReiniciar.visibility=View.INVISIBLE
            binding.botReiniciarTodo.visibility=View.INVISIBLE
            binding.textView7.text=""
            binding.textView8.text=""
        }
        binding.botReiniciarTodo.setOnClickListener {
            tiradas1=0
            tiradas2=0
            puntos1=0
            puntos2=0
            ganadas1=0
            ganadas2=0
            val imagenDado = dadoImagenes[0]
            binding.dado1.setImageResource(imagenDado!!)//fuerzo a que no es nulo y ahorro el if
            binding.dado2.setImageResource(imagenDado!!)
            binding.cajaPuntos1.setText("0")
            binding.cajaPuntos2.setText("0")
            binding.cajaTiradas1.setText("0")
            binding.cajaTiradas2.setText("0")
            binding.cajaGanadas1.setText("0")
            binding.cajaGanadas2.setText("0")
            binding.botReiniciar.visibility=View.INVISIBLE
            binding.botReiniciarTodo.visibility=View.INVISIBLE
            binding.textView7.text=""
            binding.textView8.text=""
        }
    }

    private fun aleatorio(): Int {
        return Random.nextInt(1, 7)
    }
}