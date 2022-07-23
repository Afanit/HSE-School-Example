package com.example.myapp.complex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.simple.Message
import java.util.*
import kotlin.math.absoluteValue
import kotlin.random.Random

class ComplexRecyclerViewActivity : AppCompatActivity() {
    // Создаём адаптер - он нужен нам, чтобы указывать элементы во вью
    private val adapter = ComplexMessageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complex)

        val recycler = findViewById<RecyclerView>(R.id.recycler)

        // Сообщаем RecyclerView, откуда брать информацию об элементах
        recycler.adapter = adapter
        // Сообщаем RecyclerView, каким образом отрисовывать элементы. Отображаем вертикально, снизу вверх
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // Создаём список элементов
        val messages = MutableList(2) {
            createMessage()
        }
        // Передаём нужные элементы в адаптер
        adapter.setItems(messages)

        // Добавление нового сообщения раз в секунду
        Timer().schedule(object: TimerTask() {
            override fun run() {
                // Метод для вызова в основном потоке - отрисовывать вью можно только на нём
                runOnUiThread {
                    adapter.addItem(createMessage())
                    recycler.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }, 0, 1000L)
    }

    private fun createMessage(): Item {
        return if (Random.nextBoolean()) {
            InMessage(
                name = "Not me",
                text = "Hello, me! ".repeat(Random.nextInt().absoluteValue % 15 + 1)
            )
        } else {
            OutMessage(
                text = "Hello, not me! ".repeat(Random.nextInt().absoluteValue % 15 + 1)
            )
        }
    }
}