package com.padcx.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.thecareapp.R
import kotlinx.android.synthetic.main.activity_patient_info.*

class PatientInfoActivity : BaseActivity() {

    private var specialityVO: SpecialityVO? = null
    private var day = 0
    private var month = 0
    private var year = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)

        setUpToolbar()
        specialityVO = intent.getSerializableExtra(PARAM_SPECIALITY) as SpecialityVO

        setUpSpinners()


    }

    private fun setUpSpinners() {
        val daysAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.days)
        )
        val monthsAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.months)
        )
        val yearsAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.years)
        )

        spinnerDays.adapter = daysAdapter
        spinnerDays.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                day = resources.getStringArray(R.array.days)[position].toInt()
                Log.d("DOB", "==> $day")
            }

        }

        spinnerMonths.adapter = monthsAdapter
        spinnerMonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                month = position +1
                Log.d("DOB", "==> $month")
            }

        }

        spinnerYears.adapter = yearsAdapter
        spinnerYears.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                year = resources.getStringArray(R.array.years)[position]
                Log.d("DOB", "==> $year")
            }

        }

    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        toolbar.title = resources.getString(R.string.txt_lbl_fill_patient_info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    companion object{

        const val  PARAM_SPECIALITY = "PARAM_SPECIALITY"

        fun newIntent(context: Context, specialityVO: SpecialityVO?): Intent {
            val intent = Intent(context, PatientInfoActivity::class.java)
            intent.putExtra(PARAM_SPECIALITY, specialityVO)
            return intent
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}