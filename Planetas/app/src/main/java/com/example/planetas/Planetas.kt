package com.example.planetas

 class Planetas {
    companion object{//así se hacen variables estáticas en kotlin
        @JvmStatic
        val planetas = arrayOf(
            Planeta("Mercurio", 3.3011e23, 2.439, 58, TipoPlaneta.Rocoso, 3.7),
            Planeta("Venus", 4.8675e24, 6.051, 108, TipoPlaneta.Rocoso, 8.87),
            Planeta("Tierra", 5.972e24, 6.371, 149, TipoPlaneta.Rocoso, 9.81),
            Planeta("Marte", 6.4171e23, 3.389, 227, TipoPlaneta.Rocoso, 3.71),
            Planeta("Júpiter", 1.8982e27, 69.911, 778, TipoPlaneta.Gaseoso, 24.79),
            Planeta("Saturno", 5.6834e26, 58.232, 1427, TipoPlaneta.Gaseoso, 10.44),
            Planeta("Urano", 8.6810e25, 25.362, 2871, TipoPlaneta.Helado, 8.69),
            Planeta("Neptuno", 1.02413e26, 24.622, 4495, TipoPlaneta.Helado, 11.15)
        )
    }

}