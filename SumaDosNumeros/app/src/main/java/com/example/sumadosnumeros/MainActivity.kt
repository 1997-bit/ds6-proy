package com.example.sumadosnumeros

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.sumadosnumeros.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSumar.setOnClickListener {
            val numero1 = binding.etNumero1.text.toString().toIntOrNull()
            val numero2 = binding.etNumero2.text.toString().toIntOrNull()
            if (numero1 != null && numero2 != null) {
                val suma = numero1 + numero2
                binding.tvResultado.text = "Resultado: $suma"
            } else {
                binding.tvResultado.text = "Por favor, ingrese dos números válidos"
            }
        }
    }
}