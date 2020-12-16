package com.padcx.doctors.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.padcx.doctors.R

class PrescriptionHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescription_history)
    }

    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context, PrescriptionHistoryActivity::class.java)
        }
    }
}