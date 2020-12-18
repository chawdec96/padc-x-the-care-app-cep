package com.padcx.thecareapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcx.shared.data.models.TheCareAppModel
import com.padcx.shared.data.models.TheCareAppModelImpl
import com.padcx.shared.data.vos.ConsultationRequestVO
import com.padcx.thecareapp.R
import com.padcx.thecareapp.activities.MainActivity
import com.padcx.thecareapp.adapter.CaseSummaryRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_patient_info_dialog.*
import kotlinx.android.synthetic.main.viewpod_patient_info.*

class PatientInfoDialogFragment : DialogFragment(){

    private var consultationReq: ConsultationRequestVO? = null
    private var specialityId: String ?= ""

    private lateinit var mCaseSummaryRecyclerAdapter: CaseSummaryRecyclerAdapter
    private val mTheCareAppModel: TheCareAppModel = TheCareAppModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            consultationReq = it.getSerializable(ARG_PARAM_CONSULTATION_REQUEST) as ConsultationRequestVO
            specialityId = it.getString(ARG_PARAM_SPECIALITY_ID)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_info_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        bindPatientInfo()

        setUpListener()
    }

    private fun setUpListener() {
        btnConsult.setOnClickListener {
            mTheCareAppModel.addConsultationRequest(consultationReq!!)
            startActivity(MainActivity.newIntent(this.requireContext()))
            dismiss()
        }
    }

    private fun bindPatientInfo() {
        tvPatientName.text = consultationReq?.patient?.username
        tvPatientDob.text = consultationReq?.patient?.dob
        tvPatientHeight.text = consultationReq?.patient?.height
        tvPatientBloodType.text = consultationReq?.patient?.bloodType
        tvPatientWeight.text = consultationReq?.patient?.weight
        tvPatientBloodPressure.text = consultationReq?.patient?.bloodPressure
        tvPatientAllergicMedicine.text = consultationReq?.patient?.allergicMedicine
    }

    private fun setUpRecycler() {
        mCaseSummaryRecyclerAdapter = CaseSummaryRecyclerAdapter()
        mCaseSummaryRecyclerAdapter.setNewData(consultationReq!!.caseSummary)

        rvSpecificQuestionWithAnswer.layoutManager = LinearLayoutManager(this.requireContext())
        rvSpecificQuestionWithAnswer.adapter = mCaseSummaryRecyclerAdapter
    }

    companion object {

        private const val ARG_PARAM_CONSULTATION_REQUEST = "ARG_PARAM_PATIENT"
        private const val ARG_PARAM_SPECIALITY_ID = "ARG_PARAM_SPECIALITY_ID"

        @JvmStatic
        fun newInstance(consultationRequestVO: ConsultationRequestVO, specialityId: String) =
            PatientInfoDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM_CONSULTATION_REQUEST, consultationRequestVO)
                    putString(ARG_PARAM_SPECIALITY_ID, specialityId)
                }
            }
    }
}