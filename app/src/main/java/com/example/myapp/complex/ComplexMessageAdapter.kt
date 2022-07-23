package com.example.myapp.complex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R

// Класс, сообщающий RecyclerView, сколько данных есть, какие вью создавать и как их обновлять
// Версия для отображения двух разных видов сообщений
class ComplexMessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = mutableListOf<Item>()

    // Обновляем данные
    fun setItems(messages: List<Item>) {
        items = messages.toMutableList()
        // Говорим RecyclerView, что нужно обновить содержимое
        notifyDataSetChanged()
    }

    // Обновляем данные
    fun addItem(message: Item) {
        items += message
        // Говорим RecyclerView, что нужно обновить только один элемент
        notifyItemChanged(items.size)
    }


    // Отдаём RecyclerView вью и ViewHolder, которое мы хотим использовать для отрисовки
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_IN_MESSAGE -> {
                // R.layout.item_in_message
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_message, parent, false)
                InViewHolder(view)
            }
            TYPE_OUT_MESSAGE -> {
                // R.layout.item_out_message
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_out_message, parent, false)
                OutViewHolder(view)
            }
            else -> throw IllegalStateException("Wrong viewType: $viewType!")
        }
    }

    // Обновляем состояние вью для нового Message
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Проверяем, какого типа даннй элемент и перерисовываем в соответствующем ViewHolder
        when (val message = items[position]) {
            is InMessage -> (holder as InViewHolder).bind(message)
            is OutMessage -> (holder as OutViewHolder).bind(message)
        }
    }

    // Возвращаем RecyclerView актуальное количество элементов
    override fun getItemCount(): Int {
        return items.size
    }

    // Cообщаем RecyclerView, какого типа элемент мы хотим отрисовать для данной позиции
    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is InMessage -> TYPE_IN_MESSAGE
            is OutMessage -> TYPE_OUT_MESSAGE
        }
    }

    // Объявляем типы возможных элементов
    companion object {
        private const val TYPE_IN_MESSAGE = 0
        private const val TYPE_OUT_MESSAGE = 1
    }
}

// Класс, отвечающий за отрисовку данных из InMessage
class InViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Запоминаем ссылки на нужные элементы
    private val name = itemView.findViewById<TextView>(R.id.name)
    private val text = itemView.findViewById<TextView>(R.id.text)

    // Проставляем данные из конкретного InMessage во вью
    fun bind(message: InMessage) {
        name.text = message.name
        text.text = message.text
    }
}

// Класс, отвечающий за отрисовку данных из OutMessage
class OutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Запоминаем ссылки на нужные элементы
    private val text = itemView.findViewById<TextView>(R.id.text)

    // Проставляем данные из конкретного OutMessage во вью
    fun bind(message: OutMessage) {
        text.text = message.text
    }
}


// Создаём общий интерфейс для объединения элементов, показываемых данным RecyclerView
sealed interface Item

data class InMessage(val name: String, val text: String) : Item

data class OutMessage(val text: String) : Item



