package com.padcx.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.padcx.thecareapp.R

class CheckOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
    }

    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context, CheckOutActivity::class.java)
        }
    }
}