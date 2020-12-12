package com.padcx.thecareapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.thecareapp.R
import com.padcx.thecareapp.activities.PatientInfoActivity
import kotlinx.android.synthetic.main.fragment_comfirm_consultation_dialog.*

class ConfirmConsultationDialogFragment: DialogFragment() {

    private var speciality: SpecialityVO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            speciality = it.getSerializable(ARG_PARAM_SPECIALITY) as SpecialityVO
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comfirm_consultation_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListener()

        tvQuestionForRequest.text = "${speciality?.name} ${resources.getString(R.string.txt_lbl_question_consultation)}"
        Log.d("text", "==> ${tvQuestionForRequest.text}")
    }

    private fun setUpListener() {
        btnSure.setOnClickListener {
            startActivity(PatientInfoActivity.newIntent(this.requireContext(), speciality))
            dismiss()
        }

        btnNotSure.setOnClickListener { dismiss() }
    }

    companion object {

        const val ARG_PARAM_SPECIALITY = "ARG_PARAM_SPECIALITY"

        @JvmStatic
        fun newInstance(specialityVO: SpecialityVO) =
            ConfirmConsultationDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM_SPECIALITY, specialityVO)
                }
            }
    }
}