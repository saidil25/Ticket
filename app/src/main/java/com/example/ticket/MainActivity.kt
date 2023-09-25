package com.example.ticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.ticket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var pesanButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        datePicker = binding.datePicker
        timePicker = binding.timePicker
        pesanButton = binding.pesan
        setContentView(binding.root)

        val berangkat = resources.getStringArray(R.array.berangkat)
        val tujuan = resources.getStringArray(R.array.tujuan)

        val berangkatadapter = ArrayAdapter(
            this@MainActivity,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            berangkat
        )
        berangkatadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerberangkat.adapter = berangkatadapter

        val tujuanadapter = ArrayAdapter(
            this@MainActivity,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            tujuan
        )
        tujuanadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnertujuan.adapter = tujuanadapter

        pesanButton.setOnClickListener {
            pesanTiket()
        }
    }

    private fun pesanTiket() {
        val tanggal = getDateFromDatePicker(datePicker)
        val waktu = getTimeFromTimePicker(timePicker)
        val pesan = "Pesanan berhasil dibuat pada tanggal $tanggal jam $waktu"
        showToast(pesan)
    }

    private fun getDateFromDatePicker(datePicker: DatePicker): String {
        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1
        val year = datePicker.year

        return "$day/$month/$year"
    }

    private fun getTimeFromTimePicker(timePicker: TimePicker): String {
        val hour = timePicker.hour
        val minute = timePicker.minute

        return String.format("%02d:%02d", hour, minute)
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}

