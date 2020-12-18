package com.padcx.doctors.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padcx.doctors.DoctorPrefs
import com.padcx.doctors.R
import com.padcx.doctors.TheCareDoctorApp
import com.padcx.doctors.adapters.AppointmentRecyclerAdapter
import com.padcx.doctors.adapters.ConsultationRecyclerAdapter
import com.padcx.doctors.mvp.presenters.MainPresenter
import com.padcx.doctors.mvp.presenters.impls.MainPresenterImpl
import com.padcx.doctors.mvp.views.MainView
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vos.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), MainView {

    private lateinit var mPresenter: MainPresenter
    private lateinit var mAppointmentAdapter: AppointmentRecyclerAdapter
    private lateinit var mConsultationAdapter: ConsultationRecyclerAdapter

    private var doctorVO: DoctorVO ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doctorVO = DoctorPrefs.getUserInfo()

        setUpPresenter()
        setUpRecycler()

        mPresenter.onUIReady(this, doctorVO!!.id, doctorVO?.speciality!!)

    }

    private fun setUpRecycler() {
        mAppointmentAdapter = AppointmentRecyclerAdapter(mPresenter)

        rvAppointments.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvAppointments.adapter = mAppointmentAdapter

        mConsultationAdapter = ConsultationRecyclerAdapter()

        rvConsultations.layoutManager = LinearLayoutManager(this)
        rvConsultations.adapter = mConsultationAdapter
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(MainPresenterImpl::class.java)
        mPresenter.initPresenter(this)

    }

    companion object{
        fun newIntent(context: Context) : Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun showAppointment(consultations: List<ConsultationRequestVO>) {
        mAppointmentAdapter.setNewData(consultations)
    }

    override fun showConsultations(consultations: List<ConsultationVO>) {
        mConsultationAdapter.setNewData(consultations)
    }

    override fun showPatientInfo(consultationRequestVO: ConsultationRequestVO) {
        startActivity(PatientInfoActivity.newIntent(this, consultationRequestVO))
    }

    override fun navigateToMedicineDialog() {
        TODO("Not yet implemented")
    }

    override fun navigateToMedicalHistoryDialog() {
        TODO("Not yet implemented")
    }
}
