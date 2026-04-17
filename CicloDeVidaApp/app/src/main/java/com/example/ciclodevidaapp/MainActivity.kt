package com.example.ciclodevidaapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val TAG = "CICLO_VIDA"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onCreate()")
    }
    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart()", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onStart()")
    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume()", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onResume()")
    }
    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause()", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onPause()")
    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop()", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onStop()")
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onDestroy()")
    }
}