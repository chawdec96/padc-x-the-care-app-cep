package com.padcx.doctors.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcx.doctors.R
import com.padcx.doctors.adapters.CaseSummaryRecyclerAdapter
import com.padcx.doctors.mvp.presenters.PatientInfoPresenter
import com.padcx.doctors.mvp.presenters.impls.PatientInfoPresenterImpl
import com.padcx.doctors.mvp.views.PatientInfoView
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vos.CaseSummaryVO
import com.padcx.shared.data.vos.ConsultationRequestVO
import com.padcx.shared.data.vos.PatientVO
import kotlinx.android.synthetic.main.activity_patient_info.*
import kotlinx.android.synthetic.main.viewpod_patient_info.*

class PatientInfoActivity : BaseActivity(), PatientInfoView {

    private lateinit var mPresenter: PatientInfoPresenter
    private lateinit var consultationRequestVO: ConsultationRequestVO

    private lateinit var mCaseSummaryAdapter: CaseSummaryRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        consultationRequestVO = intent.getSerializableExtra(PARAM_CONSULTATION_REQUEST) as ConsultationRequestVO

        setUpPresenter()
        setUpRecycler()

        mPresenter.onUIReady(this, consultationRequestVO)

        setUpListener()
    }

    private fun setUpListener() {
        btnStartConsult.setOnClickListener {
            mPresenter.onUpdateConsultationRequest(this, consultationRequestVO)
            mPresenter.onCreateConsultation(this, consultationRequestVO)
        }
    }

    private fun setUpRecycler() {
        mCaseSummaryAdapter = CaseSummaryRecyclerAdapter()

        rvSpecificQuestions.layoutManager = LinearLayoutManager(this)
        rvSpecificQuestions.adapter = mCaseSummaryAdapter
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(PatientInfoPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    companion object{

        const val PARAM_CONSULTATION_REQUEST = "PARAM_CONSULTATION_REQUEST"

        fun newIntent(context: Context, consultationRequestVO: ConsultationRequestVO): Intent{
            val intent = Intent(context, PatientInfoActivity::class.java)
            intent.putExtra(PARAM_CONSULTATION_REQUEST, consultationRequestVO)
            return intent
        }
    }

    override fun showPatientInfo(consultationRequestVO: ConsultationRequestVO) {

        tvPatientName.text = consultationRequestVO.patient.username
        tvPatientDob.text = consultationRequestVO.patient.dob
        tvPatientHeight.text = consultationRequestVO.patient.height
        tvPatientWeight.text = consultationRequestVO.patient.weight
        tvPatientBloodType.text = consultationRequestVO.patient.bloodType
        tvPatientBloodPressure.text = consultationRequestVO.patient.bloodPressure
        tvPatientAllergicMedicine.text = consultationRequestVO.patient.allergicMedicine

        mCaseSummaryAdapter.setNewData(consultationRequestVO.caseSummary)

    }
}