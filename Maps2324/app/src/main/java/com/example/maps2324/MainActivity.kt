package com.example.maps2324

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PointOfInterest
import com.google.android.gms.maps.model.PolylineOptions

/**
 * Implementamos:
 * GoogleMap.OnMyLocationButtonClickListener --> Dispara el evento al pulsar en el punto negro arriba a la derecha que centra el mapa en la localización acual.
 * GoogleMap.OnMyLocationClickListener --> Dispara el evento al pulsar en la localización actual, punto azul.
 * GoogleMap.OnPoiClickListener --> Dispara el evento al pulsar en puntos de interés (POI).
 * GoogleMap.OnMapLongClickListener --> Lanza el evento al pulsar en cualquier parte del mapa.
 * GoogleMap.OnMarkerClickListener --> Dispara el evento al hacer click en un marcador.
 */
class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, GoogleMap.OnPoiClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {

    private val LOCATION_REQUEST_CODE: Int = 0
    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap

    private lateinit var edLong : EditText
    private lateinit var edLat : EditText

    var alMarcadores = ArrayList<Marker>()

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        edLong = findViewById(R.id.edLongitud)
        edLat = findViewById(R.id.edLatitud)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val location = LatLng(37.7749, -122.4194) // Coordenadas de San Francisco, por ejemplo

        //googleMap.addMarker(MarkerOptions().position(location).title("Marker in San Francisco"))
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))

        map = googleMap
        //Se pueden seleccionar varios tiops de mapas:
        //  None --> no muestra nada, solo los marcadores. (MAP_TYPE_NONE)
        //  Normal --> El mapa por defecto. (MAP_TYPE_NORMAL)
        //  Satélite --> Mapa por satélite.  (MAP_TYPE_SATELLITE)
        //  Híbrido --> Mapa híbrido entre Normal y Satélite. (MAP_TYPE_HYBRID) Muestra satélite y mapas de carretera, ríos, pueblos, etc... asociados.
        //  Terreno --> Mapa de terrenos con datos topográficos. (MAP_TYPE_TERRAIN)
        map.mapType = GoogleMap.MAP_TYPE_HYBRID
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
        map.setOnPoiClickListener(this)
        map.setOnMapLongClickListener (this)
        map.setOnMarkerClickListener(this)

        enableMyLocation() //--> Hanilita, pidiendo permisos, la localización actual.
        createMarker() //--> Nos coloca varios marcadores en el mapa y nos coloca en el CIFP Virgen de Gracia con un Zoom.
        //irubicacioActual() //--> Nos coloca en la ubicación actual directamente. Comenta createMarker par ver esto.
        pintarCirculo()
        pintarRuta()
    }


    //----------------------------------------------------------------------------------------
    @SuppressLint("MissingPermission")
    fun enableMyLocation() {
        if (!::map.isInitialized) return
        if (isPermissionsGranted()) {
            map.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    /**
     * función que usaremos a lo largo de nuestra app para comprobar si el permiso ha sido aceptado o no.
     */
    fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    /**
     * Método que solicita los permisos.
     */
    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_CODE)
        }
    }

    //-----------------------------------------------------------------------------------------------------

    /**
     * Método en el que crearemos algunos marcadores de ejemplo.
     */
    private fun createMarker() {
        val markerIesMaestre = LatLng(38.991030,-3.920489 )
        /*
        Los markers se crean de una forma muy sencilla, basta con crear una instancia de un objeto LatLng() que recibirá dos
        parámetros, la latitud y la longitud. Yo en este ejemplo he puesto las coordenadas de mi playa favorita.
        */
        //map.addMarker(MarkerOptions().position(markerCIFP).title("Mi CIFP favorito!"))
        //Si queremos cambiar el color del icono, en este caso azul cyan, con un subtexto.
        val markCIFP = map.addMarker(
            MarkerOptions().position(markerIesMaestre).title("Mi instituto favorito!").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).snippet("IES MAESTRE DE CALATRAVA"))
        alMarcadores.add(markCIFP!!)

        val cr = LatLng(38.98491, -3.92862)
        //map.addMarker(MarkerOptions().position(paris).title("Paris").icon(sizeIcon(R.drawable.paris)))
        /*
        Otros atributos útiles:
            alpha(0.4f) --> Con un valor real indica la semitransparencia del icono.
            draggable(true)  --> Permite arrastralo.
            snippet("Otro texto") --> Añade un subtexto al título.
         */
        val markCR = map.addMarker(MarkerOptions().position(cr).title("Ciudad Real").icon(sizeIcon(R.drawable.cr)).alpha(0.8f).draggable(true))
        alMarcadores.add(markCR!!)

        /*
        La función animateCamera() recibirá tres parámetros:

            Un CameraUpdateFactory que a su vez llevará otros dos parámetros, el primero las coordenadas donde queremos hacer zoom
                y el segundo valor (es un float) será la cantidad de zoom que queremos hacer en dichas coordenadas.
            La duración de la animación en milisegundos, por ejemplo 4000 milisegundos son 4 segundos.
            Un listener que no vamos a utilizar, simplemente añadimos null.
         */
        //------------ Zoom hacia un marcador ------------
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(markerIesMaestre, 18f),
            4000,
            null
        )

        //Esto la mueve sin efecto zoom.
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(markerCIFP, 18f))
    }
    /**
     * Con este método vamos a ajustar el tamaño de todos los iconos que usemos en los marcadores.
     */
    fun sizeIcon(idImage:Int): BitmapDescriptor {
        val altura = 60
        val anchura = 60

        var draw = ContextCompat.getDrawable(this,idImage) as BitmapDrawable
        val bitmap = draw.bitmap  //Aquí tenemos la imagen.

        //Le cambiamos el tamaño:
        val smallBitmap = Bitmap.createScaledBitmap(bitmap, anchura, altura, false)
        return BitmapDescriptorFactory.fromBitmap(smallBitmap)

    }
//-----------------------------------------------------------------------------------------------------
    //----------------------------------------- Eventos en el mapa ----------------------------------------
    //-----------------------------------------------------------------------------------------------------

    /**
     * Se dispara cuando pulsamos la diana que nos centra en el mapa (punto negro, arriba a la derecha en forma de diana).
     */
    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Recentrando", Toast.LENGTH_SHORT).show()
        return false
    }

    /**
     * Se dispara cuando pulsamos en nuestra localización exacta donde estámos ahora (punto azul).
     */
    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Estás en ${p0.latitude}, ${p0.longitude}", Toast.LENGTH_SHORT).show()
        this.edLong.setText(p0.longitude.toString())
        this.edLat.setText(p0.latitude.toString())
    }

    /**
     * Con el parámetro podremos obtener información del punto de interés. Este evento se lanza cuando pulsamos en un POI.
     */
    override fun onPoiClick(p0: PointOfInterest) {
        Toast.makeText(this@MainActivity, "Pulsado.", Toast.LENGTH_LONG).show()
        val dialogBuilder = AlertDialog.Builder(this@MainActivity)
        dialogBuilder.run {
            setTitle("Información del lugar.")
            setMessage("Id: " + p0!!.placeId + "\nNombre: " + p0!!.name + "\nLatitud: " + p0!!.latLng.latitude.toString() + " \nLongitud: " + p0.latLng.longitude.toString())
            setPositiveButton("Aceptar"){ dialog: DialogInterface, i:Int ->
                Toast.makeText(this@MainActivity, "Salir", Toast.LENGTH_LONG).show()
            }
        }
        dialogBuilder.create().show()
        this.edLong.setText(p0.latLng.longitude.toString())
        this.edLat.setText(p0.latLng.latitude.toString())
    }

    /**
     * Con el parámetro crearemos un marcador nuevo. Este evento se lanzará al hacer un long click en alguna parte del mapa.
     */
    override fun onMapLongClick(p0: LatLng) {
        var marcador = map.addMarker(MarkerOptions().position(p0!!).title("Nuevo marcador"))
        alMarcadores.add(marcador!!)
        this.edLong.setText(p0!!.longitude.toString())
        this.edLat.setText(p0!!.latitude.toString())
        Log.e("ACSCO","Marcador añadido, marcadores actuales: ${alMarcadores.toString()}")
    }

    /**
     * Este evento se lanza cuando hacemos click en un marcador.
     */
    override fun onMarkerClick(p0: Marker): Boolean {
        Toast.makeText(this, "Estás en ${p0!!.title}, ${p0!!.position}", Toast.LENGTH_SHORT).show()
        this.edLong.setText(p0.position.longitude.toString())
        this.edLat.setText(p0.position.latitude.toString())
        p0.remove()  //---> Para borrarlo cuando hago click sobre él solo hay que descomentar esto.
        alMarcadores.removeAt(alMarcadores.indexOf(p0))
        Log.e("ACSCO","Marcador eliminado, marcadores actuales: ${alMarcadores.toString()}")

        return true;
    }

    /**
     * Nos coloca en la ubicación actual.
     */
    @SuppressLint("MissingPermission")
    private fun irubicacioActual() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val miUbicacion = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val latLng = LatLng(miUbicacion!!.latitude, miUbicacion.longitude)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f)) //--> Mueve la cámara a esa posición, sin efecto. El valor real indica el nivel de Zoom, de menos a más.
    }

    //------------------------------------------------------------------------------------------------------

    /**
     * Dibuja una línea recta desde nuestra ubicación actual al CIFP Virgen de Gracia.
     */
    @SuppressLint("MissingPermission")
    fun pintarRuta(){
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val miUbicacion = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val latLng = LatLng(miUbicacion!!.latitude, miUbicacion.longitude)
        val markerInstituto = LatLng(38.991030,-3.920489)

        map.addPolyline(PolylineOptions().run{
            add(latLng, markerInstituto)
            color(Color.BLUE)
            width(9f)
        })

        val loc1 = Location("")
        loc1.latitude = latLng.latitude
        loc1.longitude = latLng.longitude
        val loc2 = Location("")
        loc2.latitude = markerInstituto.latitude
        loc2.longitude = markerInstituto.longitude
        val distanceInMeters = loc1.distanceTo(loc2)
        Log.e("ACSCO", distanceInMeters.toString())
    }

    /**
     * Dibuja una línea recta desde nuestra ubicación actual al CIFP Virgen de Gracia.
     */
    fun pintarCirculo(){
        val markerInstituto = LatLng(38.991030,-3.920489)

        map.addCircle(CircleOptions().run{
            center(markerInstituto)
            radius(9.0)
            strokeColor(Color.BLUE)
            fillColor(Color.GREEN)
        })
    }

}