package modelo

object Personas
{
    var personas = ArrayList<Persona>()
    fun aniadirPersona(p:Persona){
        personas.add(p)
    }
}