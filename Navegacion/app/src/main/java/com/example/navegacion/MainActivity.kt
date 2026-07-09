package com.example.navegacion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.navegacion.conversionmoneda.ConversionMonedaActivity
import com.example.navegacion.conversionpeso.ConversionPesoActivity
import com.example.navegacion.encuesta.EncuestaActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<android.widget.Button>(R.id.btnEncuesta).setOnClickListener {
            startActivity(Intent(this, EncuestaActivity::class.java))
        }

        findViewById<android.widget.Button>(R.id.btnConversionPeso).setOnClickListener {
            startActivity(Intent(this, ConversionPesoActivity::class.java))
        }

        findViewById<android.widget.Button>(R.id.btnConversionMoneda).setOnClickListener {
            startActivity(Intent(this, ConversionMonedaActivity::class.java))
        }
    }
}
