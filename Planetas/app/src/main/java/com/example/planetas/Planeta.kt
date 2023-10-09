package com.example.planetas
// Definir una enumeración para los tipos de planetas
enum class TipoPlaneta {
    Rocoso, Gaseoso, Helado
}

// Definir la clase Planeta
class Planeta(
    val nombre: String,
    val masa: Double, // en kilogramos
    val radio: Double, // en metros
    val distanciaAlSol: Int, // en milones kilómetros
    val tipo: TipoPlaneta,
    val gravedad: Double // en m/s^2
   ) {


    override fun toString(): String {
        return "Planeta(nombre='$nombre', masa=$masa, radio=$radio, distanciaAlSol=$distanciaAlSol, tipo=$tipo, gravedad=$gravedad)"
    }
}


