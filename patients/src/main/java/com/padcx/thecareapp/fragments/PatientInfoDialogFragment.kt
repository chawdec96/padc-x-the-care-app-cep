package com.padcx.thecareapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcx.shared.data.models.TheCareAppModel
import com.padcx.shared.data.models.TheCareAppModelImpl
import com.padcx.shared.data.vos.CaseSummaryVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.SpecificQuestionVO
import com.padcx.thecareapp.R
import com.padcx.thecareapp.activities.MainActivity
import com.padcx.thecareapp.adapter.SpecificQuestionRecyclerAdapter
import com.padcx.thecareapp.delegates.SpecificQuestionItemDelegate
import kotlinx.android.synthetic.main.fragment_patient_info_dialog.*
import kotlinx.android.synthetic.main.viewpod_patient_info.*

class PatientInfoDialogFragment : DialogFragment(), SpecificQuestionItemDelegate {

    private var patient: PatientVO? = null
    private var specialityId: String ?= ""
    private var specificQuestions: List<SpecificQuestionVO>? = arrayListOf()

    private lateinit var mSpecificQuestionRecyclerAdapter: SpecificQuestionRecyclerAdapter
    private val mTheCareAppModel: TheCareAppModel = TheCareAppModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            patient = it.getSerializable(ARG_PARAM_PATIENT) as PatientVO
            specificQuestions = it.getParcelableArrayList(ARG_PARAM_SPECIFIC_QUESTIONS)
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
            // create consultation request
            // insert questions to patient
            patient?.let { patient ->
                mTheCareAppModel.setSpecificQuestions(patient.id, specificQuestions!!)
                mTheCareAppModel.getSpecificQuestionsForPatient(patient.id, onSuccess = {
                    patient.specificQuestions?.addAll(it)
                },onFailure = {
                    Log.d("Fail", "==> $it")
                    Toast.makeText(this.requireContext(), it, Toast.LENGTH_SHORT).show()
                })

                mTheCareAppModel.addConsultationRequestByPatient(CaseSummaryVO(), patient, specialityId!!)
                startActivity(MainActivity.newIntent(this.requireContext()))
            }

        }
    }

    private fun bindPatientInfo() {
        tvPatientName.text = patient?.username
        tvPatientDob.text = patient?.dob
        tvPatientHeight.text = patient?.height
        tvPatientBloodType.text = patient?.bloodType
        tvPatientWeight.text = patient?.weight
        tvPatientBloodPressure.text = patient?.bloodPressure
        tvPatientAllergicMedicine.text = patient?.allergicMedicine
    }

    private fun setUpRecycler() {
        mSpecificQuestionRecyclerAdapter = SpecificQuestionRecyclerAdapter(this, "answer")
        mSpecificQuestionRecyclerAdapter.setNewData(specificQuestions!!)

        rvSpecificQuestionWithAnswer.layoutManager = LinearLayoutManager(this.requireContext())
        rvSpecificQuestionWithAnswer.adapter = mSpecificQuestionRecyclerAdapter
    }

    companion object {

        private const val ARG_PARAM_PATIENT = "ARG_PARAM_PATIENT"
        private const val ARG_PARAM_SPECIFIC_QUESTIONS = "ARG_PARAM_SPECIFIC_QUESTIONS"
        private const val ARG_PARAM_SPECIALITY_ID = "ARG_PARAM_SPECIALITY_ID"

        @JvmStatic
        fun newInstance(patient: PatientVO, specificQuestions: ArrayList<SpecificQuestionVO>, specialityId: String) =
            PatientInfoDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM_PATIENT, patient)
                    putParcelableArrayList(ARG_PARAM_SPECIFIC_QUESTIONS, specificQuestions)
                    putString(ARG_PARAM_SPECIALITY_ID, specialityId)
                }
            }
    }

    override fun onTapAnswerEditText(specificQuestionVO: SpecificQuestionVO) {
        TODO("Not yet implemented")
    }
}