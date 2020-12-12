package com.padcx.doctors.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.padcx.doctors.R

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }

    companion object{
        const val TAG = "ChatActivity"

        fun newIntent(context: Context): Intent {
            return Intent(context, ChatActivity::class.java)
        }
    }
}