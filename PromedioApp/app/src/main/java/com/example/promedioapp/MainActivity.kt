package com.example.promedioapp  // Changed from sumadosnumeros to promediosapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.promedioapp.databinding.ActivityMainBinding  // Updated package

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar layout correctamente
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {

            val n1 = binding.editNota1.text.toString().toDoubleOrNull() ?: 0.0
            val n2 = binding.editNota2.text.toString().toDoubleOrNull() ?: 0.0
            val n3 = binding.editNota3.text.toString().toDoubleOrNull() ?: 0.0
            val n4 = binding.editNota4.text.toString().toDoubleOrNull() ?: 0.0

            val promedio = (n1 + n2 + n3 + n4) / 4

            binding.txtResultado.text = "Resultado: $promedio"
        }
    }
}