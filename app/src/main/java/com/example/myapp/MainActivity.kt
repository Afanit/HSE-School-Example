package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapp.complex.ComplexRecyclerViewActivity
import com.example.myapp.simple.SimpleRecyclerViewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.simple).setOnClickListener {
            startActivity(Intent(this, SimpleRecyclerViewActivity::class.java))
        }

        findViewById<Button>(R.id.complex).setOnClickListener {
            startActivity(Intent(this, ComplexRecyclerViewActivity::class.java))
        }
    }
}