package com.example.activitycommunication

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class SecondActivityContract : ActivityResultContract<String, String?>() {
    override fun createIntent(context: Context, hint: String): Intent {
        // Отправляем параметры в SecondActivity
        return Intent(context, SecondActivity::class.java).putExtra(HINT_KEY, hint)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        // Получаем параметры из SecondActivity
        if (resultCode != Activity.RESULT_OK) return null
        return intent?.extras?.getString(INPUT_KEY)
    }

    companion object {
        const val HINT_KEY = "hint"
        const val INPUT_KEY = "input"
    }
}