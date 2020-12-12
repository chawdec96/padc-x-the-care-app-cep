package com.padcx.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.shared.data.models.TheCareAppModel
import com.padcx.shared.data.models.TheCareAppModelImpl
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcx.thecareapp.mvp.presenters.HomePresenter
import com.padcx.thecareapp.mvp.views.HomeView

class HomePresenterImpl: HomePresenter, AbstractBasePresenter<HomeView>() {

    private val mTheCareAppModel: TheCareAppModel = TheCareAppModelImpl

    override fun onUIReady(owner: LifecycleOwner) {
        mTheCareAppModel.getSpecialitiesFromFirebaseAndSaveToDB {
            mView?.showError(it)
        }

        mTheCareAppModel.getSpecialities()
            .observe(owner, Observer {
                mView?.showSpecialities(it)
            })
    }

    override fun getRecentDoctors(owner: LifecycleOwner) {
        val doctors = arrayListOf<DoctorVO>()
        mView?.showRecentDoctors(doctors)
    }

    override fun getConsultations(owner: LifecycleOwner) {
        TODO("Not yet implemented")
    }

    override fun onTapItem(specialityVO: SpecialityVO) {
        mView?.showConfirmConsultationDialog(specialityVO)
    }
}