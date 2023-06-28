package ru.paramonov.brodcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.paramonov.brodcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == MyService.ACTION_LOADED) {
                val percent = intent.getIntExtra(MyService.PERCENT, 0)
                binding.progressBar.progress = percent
            }
        }
    }

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
            addAction(MyService.ACTION_LOADED)
        }

        registerReceiver(receiver, intentFilter)
        Intent(this, MyService::class.java).apply {
            startService(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}