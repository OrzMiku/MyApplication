package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private val mTag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(mTag, "onCreate Called")
        setContentView(R.layout.main_layout)
        val btnGreeting: Button = findViewById(R.id.btn_greeting)
        btnGreeting.setOnClickListener{
            Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(mTag, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(mTag, "onResume Called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(mTag, "onRestoreInstanceState Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(mTag, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(mTag, "onPause Called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(mTag, "onSaveInstanceState Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(mTag, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(mTag, "onDestroy Called")
    }
}