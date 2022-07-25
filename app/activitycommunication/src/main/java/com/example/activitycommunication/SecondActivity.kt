package com.example.activitycommunication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.example.activitycommunication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private var text: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получаем подсказку из первой активити и проставляем в EditText
        val hint = intent.extras?.getString(SecondActivityContract.HINT_KEY)
        binding.input.hint = hint

        // Запоминаем введённый пользователем текст
        binding.input.doOnTextChanged { text, _, _, _ ->
            this.text = text.toString()
        }

        // На кнопку "Отправить" отправляем результат в первую активити
        binding.send.setOnClickListener {
            // Сохраняем результат в Intent
            val result = Intent().putExtra(
                SecondActivityContract.INPUT_KEY,
                text
            )
            // Указываем, что всё ок
            setResult(Activity.RESULT_OK, result)
            // Завершаем текущую активити
            finish()
        }
    }
}