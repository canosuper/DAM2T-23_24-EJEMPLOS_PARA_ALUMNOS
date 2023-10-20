package com.example.variosactivitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.variosactivitys.databinding.ActivityMainBinding
import modelo.Persona

class MainActivity : AppCompatActivity() {
    /*
    * En este ejemplo veremos como pasar datos de una Ventana a otra (botón Añadir)
    * Los datos los podemos pasar bien de manera individual o en un objeto, que es como ha quedado la versión
    * definitiva de este ejemplo.
    *
    * Además enseñamos a ir a la segunda ventana esperando datos de la misma de dos maneras:
    *   - Estilo Deprecated pero que a veces da menos problemas.
    *   - Estilo Actual
    * En ambos casos la Ventana2 devuelve los datos a la primera de la misma manera.
    * */

    lateinit var binding: ActivityMainBinding
    private val SECOND_ACTIVITY_REQUEST_CODE = 0

    //Esta variable es necesaria para la llamada y espera de forma actual. Se crea como variable de clase.
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            // Get String data from Intent
            val returnString = data!!.getStringExtra("keyName")
            //val returnString = data!!.getSerializableExtra("objeto")
            // Set text view with string
            binding.cajaTextoDevuelto.setText(returnString)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ACSCO", "ONCREATE(), Ventana 1")
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.boton.setOnClickListener {
            irAVentana2()
        }

        //Con este método llamamos a la segunda ventana y esperamos que nos devuelva algo.
        //Usamos la forma deprecated, pero todavía vigente, de: startActivityForResult.
        //Lo que nos devuelva la segunda ventana será tratado en el método onActivityResult (un poco más abajo).
        binding.btEsperaRespuestaDepre.setOnClickListener {
            // Start the SecondActivity
            var miIntent: Intent = Intent(this, Ventana2::class.java)
            var p = Persona(binding.cajaNombre.text.toString(), binding.cajaEdad.text.toString())
            miIntent.putExtra("obj",p)
            startActivityForResult(miIntent, SECOND_ACTIVITY_REQUEST_CODE)

        }
        //A continuación es la manera actual de hacerlo.
        binding.btEsperaRespuestaActual.setOnClickListener {
            // Start the SecondActivity
            var miIntent: Intent = Intent(this, Ventana2::class.java)
            var p = Persona(binding.cajaNombre.text.toString(), binding.cajaEdad.text.toString())
            miIntent.putExtra("obj",p)
            resultLauncher.launch(miIntent)
        }

    }

    private fun irAVentana2() {
        //Este método nos lleva a la ventana de manea normal, pasando datos pero no esperas que la segunda devuelva datos.
        //comentado está pasando los atributos individualmente. he optado por pasar un objeto Persona (debe ser serializable)
        var miIntent: Intent = Intent(this, Ventana2::class.java)
        //miIntent.putExtra("nombre", binding.cajaNombre.text.toString())
        //miIntent.putExtra("edad", binding.cajaEdad.text.toString())
        var p = Persona(binding.cajaNombre.text.toString(), binding.cajaEdad.text.toString())
        miIntent.putExtra("obj",p)
        startActivity(miIntent)
    }

    // This method is called when the second activity finishes
    //Este método se invoca automáticamente al venir de la segunda ventana, si usamos la manera obsleta(deprecated)
    //Si os fijaís el códig interno es muy similar a la variable declarada arriba que se utiliza si lo hacemos de
    //la manera actual
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Este es llamado si utilizamos la manera Deprecated. Una vez vienes de la ventana2 entra aqui.
        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Get String data from Intent
                val returnString = data!!.getStringExtra("keyName")
                // Set text view with string
                binding.cajaTextoDevuelto.setText(returnString)
            }
        }
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