package ru.paramonov.brodcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.paramonov.brodcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val receiver = MyReceiver()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.clickButton.setOnClickListener {
            MyReceiver.newReceiver(MyReceiver.ACTION_CLICKED, count++).apply {
                sendBroadcast(this)
            }
        }

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(MyReceiver.ACTION_CLICKED)
        }
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}