package ru.paramonov.brodcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            ACTION_CLICKED -> {
                val count = intent.getIntExtra(COUNT_CLICK, 0)
                Toast.makeText(
                    context,
                    "Clicked: $count",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val state = intent.getBooleanExtra("state", false)
                Toast.makeText(
                    context,
                    "Airplane mode changed, Mode On: $state",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(
                    context,
                    "Low Battery",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {

        const val ACTION_CLICKED = "clicked"
        private const val COUNT_CLICK = "click"

        fun newReceiver(action: String, count: Int) = Intent(action).apply {
            putExtra(COUNT_CLICK, count)
        }
    }
}