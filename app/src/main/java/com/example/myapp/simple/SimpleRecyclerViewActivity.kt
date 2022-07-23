package com.example.myapp.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R

class SimpleRecyclerViewActivity : AppCompatActivity() {

    // Создаём адаптер - он нужен нам, чтобы обновлять элементы во вью
    private val adapter = SimpleMessageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)

        val recycler = findViewById<RecyclerView>(R.id.recycler)

        // Сообщаем RecyclerView, откуда брать информацию об элементах
        recycler.adapter = adapter
        // Сообщаем RecyclerView, каким образом отрисовывать элементы
        recycler.layoutManager = LinearLayoutManager(this)

        // Создаём список элементов
        val messages = MutableList(15) {
            if (it % 2 == 0) {
                Message(
                    icon = R.drawable.ic_launcher_foreground,
                    name = "Recycler",
                    text = "Hello".repeat(15)
                )
            } else {
                Message(
                    icon = R.drawable.ic_launcher_foreground,
                    name = "View",
                    text = "World!"
                )
            }
        }
        // Передаём нужные элементы в адаптер
        adapter.setItems(messages)
    }
}

