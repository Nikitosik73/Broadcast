package ru.paramonov.brodcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val receiver = MyReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val airPlaneMode = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        val lowBattery = IntentFilter(Intent.ACTION_BATTERY_LOW)
        registerReceiver(receiver, airPlaneMode)
        registerReceiver(receiver, lowBattery)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}