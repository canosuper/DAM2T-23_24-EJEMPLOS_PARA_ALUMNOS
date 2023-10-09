package com.example.seekyprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import com.example.seekyprogressbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.caja1.setText("Mensaje puesto desde el Binding")
        //Log.d("ACSCO",getDrawable(R.drawable.martin2).toString())
        binding.seekBarDiscreta.setOnSeekBarChangeListener(object:
        SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                binding.caja1.setText("Cambiando Progreso")
            }

            override fun onStartTrackingTouch(p0: SeekBar) {
                binding.caja2.setText("Cambiando Progreso mientras deslizo")
            }

            override fun onStopTrackingTouch(p0: SeekBar) {
                binding.caja2.setText("Dejo de deslizar")
                binding.caja3.setText(binding.seekBarDiscreta.progress.toString())
            }

        })

        binding.seekBarContinua.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                binding.caja1.setText("Cambiando progreso")
                var progreso = binding.seekBarContinua.progress / 10.0
                binding.progressBar.progress = progreso.toInt()
                binding.caja2.setText(progreso.toString())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                binding.caja2.setText("Starting tracking")
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                binding.caja2.setText("Stopping tracking")
                var progreso = binding.seekBarContinua.progress / 10.0
                binding.caja1.setText(progreso.toString())

            }
        })

        binding.img1.setOnClickListener {
            //this.binding.img1.setImageDrawable(resources.getDrawable(R.drawable.martin2)) Deprecated
            //this.binding.img1.setImageDrawable(getDrawable(R.drawable.martin2)) Acceder al drawable para cambiar fuente de img

            this.binding.img1.visibility= View.INVISIBLE
            this.binding.img2.visibility= View.VISIBLE

            Log.d("ACSCO",getDrawable(R.drawable.martin2).toString())

        }
        binding.img2.setOnClickListener {

            this.binding.img1.visibility= View.VISIBLE
            this.binding.img2.visibility= View.INVISIBLE

            Log.d("ACSCO",getDrawable(R.drawable.martin2).toString())

        }


    }
}