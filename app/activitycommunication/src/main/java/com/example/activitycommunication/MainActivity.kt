package com.example.activitycommunication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.activitycommunication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var text: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Указываем действия с результатом при получении
        val launcher = registerForActivityResult(SecondActivityContract()) {
            Toast.makeText(this@MainActivity, "Получено $it", Toast.LENGTH_SHORT).show()
        }

        // Запоминаем введённый пользователем вопрос
        binding.input.doOnTextChanged { text, _, _, _ ->
            this.text = text.toString()
        }

        // Запускаем SecondActivity по нажатию
        binding.button.setOnClickListener {
            launcher.launch(text)
        }
    }
}