package com.padcx.thecareapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vos.*
import com.padcx.thecareapp.R
import com.padcx.thecareapp.adapter.SpecificQuestionRecyclerAdapter
import com.padcx.thecareapp.fragments.PatientInfoDialogFragment
import com.padcx.thecareapp.mvp.presenters.PatientInfoPresenter
import com.padcx.thecareapp.mvp.presenters.impls.PatientInfoPresenterImpl
import com.padcx.thecareapp.mvp.views.PatientInfoView
import com.padcx.thecareapp.persistence.PatientPrefs
import kotlinx.android.synthetic.main.activity_patient_info.*
import kotlinx.android.synthetic.main.viewpod_patient_info.*

class PatientInfoActivity : BaseActivity(), PatientInfoView {

    private var specialityVO: SpecialityVO? = null
    private lateinit var patientVO: PatientVO

    private lateinit var mSpecificQuestionAdapter: SpecificQuestionRecyclerAdapter

    private var day = 1
    private var month = 1
    private var year = "1996"
    private var bloodType = "A"
    private var specificQuesWithAns: ArrayList<CaseSummaryVO> = arrayListOf()


    private lateinit var mPresenter: PatientInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)

        setUpToolbar()
        specialityVO = intent.getSerializableExtra(PARAM_SPECIALITY) as SpecialityVO
        patientVO = PatientPrefs.getUserInfo()

        setUpPresenter()

        if (patientVO.dob.isNullOrEmpty() || patientVO.height.isNullOrEmpty()
            || patientVO.bloodType.isNullOrEmpty() || patientVO.allergicMedicine.isNullOrEmpty()
        ) {
            tvDob.visibility = View.VISIBLE
            llDob.visibility = View.VISIBLE

            tvHeight.visibility = View.VISIBLE
            etHeight.visibility = View.VISIBLE

            tvBloodType.visibility = View.VISIBLE
            spinnerBloodType.visibility = View.VISIBLE

            tvAllergicMedicine.visibility = View.VISIBLE
            flAllergicMedicine.visibility = View.VISIBLE

            llStep1Summary.visibility = View.GONE

            setUpSpinners()
            etAllergicMedicine.setOnEditorActionListener { textView, actionId, keyEvent ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE ||
                    keyEvent != null &&
                    keyEvent.action == KeyEvent.ACTION_DOWN &&
                    keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    if (keyEvent == null || !keyEvent.isShiftPressed) {
                        // the user is done typing.
                        mPresenter.onTapEditText(this)
                        true // consume.
                    }
                }
                false
            }
        } else {
            bindPatientData()
        }

        setUpListener()
        setUpRecycler()

    }

    private fun bindPatientData() {
        tvPatientName.text = patientVO.username
        tvPatientDob.text = patientVO.dob
        tvPatientBloodType.text = patientVO.bloodType
        tvPatientHeight.text = patientVO.height
        tvAllergicMedicine.text = patientVO.allergicMedicine
    }

    private fun setUpRecycler() {
        mSpecificQuestionAdapter = SpecificQuestionRecyclerAdapter(mPresenter)

        val layoutManager = LinearLayoutManager(this)
        rvSpecificQuestions.layoutManager = layoutManager
        rvSpecificQuestions.addItemDecoration(
            DividerItemDecoration(
                rvSpecificQuestions.context, layoutManager.orientation
            )
        )
        rvSpecificQuestions.adapter = mSpecificQuestionAdapter
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(PatientInfoPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener() {
        btnContinue.setOnClickListener {
            llStep1.visibility = View.GONE
            llStep2.visibility = View.VISIBLE
            ivStep2.setImageDrawable(resources.getDrawable(R.drawable.checked))
            view2.setBackgroundColor(resources.getColor(R.color.accent))

            patientVO.let {
                mPresenter.onTapBtnContinue(
                    this, specialityVO!!.id, it.email!!, it.password!!, tvPatientName.text.toString(), it.phone!!,
                    it.image!!, tvPatientDob.text.toString(), tvPatientHeight.text.toString(),
                    tvPatientBloodType.text.toString(), etWeight.text.toString(), etBloodPressure.text.toString(),
                    etAllergicMedicine.text.toString()
                )
            }
        }

        btnConsult.setOnClickListener {
            patientVO = PatientPrefs.getUserInfo()

            mPresenter.onTapBtnConsultant(this, ConsultationRequestVO(
                "reqId${patientVO.phone}", patientVO, specialityVO!!.id, specificQuesWithAns, false
            ))
        }
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

        val bloodTypeAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.blood_types)
        )

        spinnerDays.adapter = daysAdapter
        spinnerDays.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                day = resources.getStringArray(R.array.days)[position].toInt()
                Log.d("DOB", "==> $day")
            }

        }

        spinnerMonths.adapter = monthsAdapter
        spinnerMonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                month = position + 1
                Log.d("DOB", "==> $month")
            }

        }

        spinnerYears.adapter = yearsAdapter
        spinnerYears.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                year = resources.getStringArray(R.array.years)[position]
                Log.d("DOB", "==> $year")
            }

        }

        spinnerBloodType.adapter = bloodTypeAdapter
        spinnerBloodType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                bloodType = resources.getStringArray(R.array.blood_types)[position]
            }

        }

    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        toolbar.title = resources.getString(R.string.txt_lbl_fill_patient_info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    companion object {

        const val PARAM_SPECIALITY = "PARAM_SPECIALITY"

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

    override fun showPatientInfo() {
        tvDob.visibility = View.GONE
        llDob.visibility = View.GONE

        tvHeight.visibility = View.GONE
        etHeight.visibility = View.GONE

        tvBloodType.visibility = View.GONE
        spinnerBloodType.visibility = View.GONE

        tvAllergicMedicine.visibility = View.GONE
        flAllergicMedicine.visibility = View.GONE

        llStep1Summary.visibility = View.VISIBLE

        tvPatientName.text = patientVO.username
        tvPatientDob.text = "$day/ $month/ $year"
        tvPatientHeight.text = etHeight.text.toString()
        tvPatientBloodType.text = bloodType
        tvPatientAllergicMedicine.text = etAllergicMedicine.text.toString()

    }

    override fun showSpecificQuestions(specificQuestions: List<SpecificQuestionVO>) {
        mSpecificQuestionAdapter.setNewData(specificQuestions)
    }

    override fun addSpecificQuestionsWithAnswer(caseSummaryVO: CaseSummaryVO) {
        specificQuesWithAns.add(caseSummaryVO)
    }

    override fun showPatientInfoDialog(consultationRequestVO: ConsultationRequestVO) {
        PatientInfoDialogFragment.newInstance(consultationRequestVO, specialityVO!!.id)
            .show(supportFragmentManager, "dialog")
    }
}