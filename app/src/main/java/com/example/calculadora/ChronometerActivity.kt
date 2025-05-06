package com.example.calculadora


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

class ChronometerActivity : AppCompatActivity() {
    private lateinit var chronometer: Chronometer
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnReset: Button
    private var running = false
    private var pauseOffset: Long = 0
    private lateinit var tvFechaHora: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cronometro)

        chronometer = findViewById(R.id.chronometer)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)

        tvFechaHora = findViewById(R.id.tvFechaHora)

        updateFechaHora(tvFechaHora)
        btnStart.setOnClickListener {
            if (!running) {
                chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
                chronometer.start()
                running = true
            }
        }

        btnStop.setOnClickListener {
            if (running) {
                chronometer.stop()
                pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
                running = false
            }
        }

        btnReset.setOnClickListener {
            chronometer.base = SystemClock.elapsedRealtime()
            pauseOffset = 0
            updateFechaHora(tvFechaHora)
        }
    }
}

fun updateFechaHora(textView: TextView) {
    val currentDate = Date()
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    textView.text = formatter.format(currentDate)
}








