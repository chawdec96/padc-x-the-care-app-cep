package com.padcx.thecareapp.mvp.views

import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.shared.mvp.views.BaseView

interface MainView: BaseView {

    fun showSpecialities(specialities: List<SpecialityVO>)
}