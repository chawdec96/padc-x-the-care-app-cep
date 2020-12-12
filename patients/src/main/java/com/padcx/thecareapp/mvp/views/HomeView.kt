package com.padcx.thecareapp.mvp.views

import com.padcx.shared.data.vos.ConsultationVO
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.shared.mvp.views.BaseView

interface HomeView: BaseView {

    fun showSpecialities(specialities: List<SpecialityVO>)
    fun showRecentDoctors(doctors: List<DoctorVO>)
    fun showConsultations(consultations: List<ConsultationVO>)

    fun showConfirmConsultationDialog(specialityVO: SpecialityVO)
}