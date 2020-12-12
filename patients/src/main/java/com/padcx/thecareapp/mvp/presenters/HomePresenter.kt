package com.padcx.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.shared.mvp.presenters.BasePresenter
import com.padcx.thecareapp.delegates.SpecialityItemDelegate
import com.padcx.thecareapp.mvp.views.HomeView

interface HomePresenter: BasePresenter<HomeView>, SpecialityItemDelegate {

    fun onUIReady(owner: LifecycleOwner)
    fun getRecentDoctors(owner: LifecycleOwner)
    fun getConsultations(owner: LifecycleOwner)
}