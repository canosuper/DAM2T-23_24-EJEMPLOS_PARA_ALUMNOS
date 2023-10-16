package com.example.imagenes

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imagenes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mediaplayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaplayer = MediaPlayer.create(this, R.raw.tos)
        binding.imageButton.setOnClickListener {
            mediaplayer.start()
        }
        binding.imageButton2.setOnClickListener {
            mediaplayer.stop()
            mediaplayer = MediaPlayer.create(this, R.raw.tos)

        }
    }
}