package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageButton.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.ic_rugby_green)
        }
        binding.imageButton2.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.ic_rugby_red)
        }
    }
}