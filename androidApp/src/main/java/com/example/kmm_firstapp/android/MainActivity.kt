package com.example.kmm_firstapp.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kmm_firstapp.Greeting
import android.widget.TextView
import android.widget.Toast
import com.example.kmm_firstapp.Yeison
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

fun nameYeison(): String {
    return Yeison().name()
}

class MainActivity : AppCompatActivity() {

    private val greeting = Greeting()

    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "Loading..."

        mainScope.launch {
            kotlin.runCatching {
                greeting.greeting()
            }.onSuccess {
                tv.text = it
            }.onFailure {
                tv.text = "Error: ${it.localizedMessage}"
            }

            Toast.makeText(this@MainActivity, "hello2", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}
