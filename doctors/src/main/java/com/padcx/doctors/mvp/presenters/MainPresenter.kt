package com.padcx.doctors.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctors.delegates.AppointmentItemDelegate
import com.padcx.doctors.mvp.views.MainView
import com.padcx.shared.data.vos.ConsultationRequestVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface MainPresenter: BasePresenter<MainView>, AppointmentItemDelegate {

    fun onUIReady(owner: LifecycleOwner, doctorId: String, specialityId: String)

}