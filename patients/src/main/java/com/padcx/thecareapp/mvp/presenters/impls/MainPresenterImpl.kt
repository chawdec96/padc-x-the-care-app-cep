package com.padcx.thecareapp.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.AuthenticationModelImpl
import com.padcx.shared.data.models.TheCareAppModel
import com.padcx.shared.data.models.TheCareAppModelImpl
import com.padcx.shared.data.vos.*
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcx.shared.network.FirebaseApi
import com.padcx.shared.network.impls.FirebaseCloudFirestoreApiImpl
import com.padcx.shared.utils.NODE_CHECK_OUT
import com.padcx.thecareapp.mvp.presenters.MainPresenter
import com.padcx.thecareapp.mvp.views.MainView
import com.padcx.thecareapp.persistence.PatientPrefs

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {

    private val mTheCareModelImpl: TheCareAppModel = TheCareAppModelImpl
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    override fun onUIReady(owner: LifecycleOwner, patientId: String) {

//        mTheCareModelImpl.getPatient(mAuthenticationModel.getUserEmail(),onSuccess = {
//            PatientPrefs.setUserInfo(it)
//            PatientPrefs.setUserId(it.id)
//        }, onFailure = {
//            mView?.showError(it)
//        })

        mTheCareModelImpl.getGeneralQuestions(patientId, onSuccess = {
            mView?.addGeneralQuestionsToPatientVO(it)
        }, onFailure = {

        })

        mTheCareModelImpl.getRecentlyDoctors(patientId, onSuccess = {
            mView?.addRecentlyDoctorsToPatientVO(it)
        }, onFailure = {
            mView?.showError(it)
        })
    }
//    }

//    override fun getGeneralQuestions(owner: LifecycleOwner, patientId: String) {
//
//    }
//
//    override fun getRecentlyDoctors(owner: LifecycleOwner, patientId: String) {
//    }

//    override fun broadcastConsultationRequest(
//        caseSummaryVO: CaseSummaryVO,
//        patientVO: PatientVO,
//        specialityId: String
//    ) {
//        mTheCareModelImpl.addConsultationRequestByPatient(
//            caseSummaryVO, patientVO, specialityId
//        )
//    }

    /*
    override fun chatMessage(consultationId: String, chatVO: ChatVO) {
        mTheCareModelImpl.setChatMessage(consultationId, chatVO)
    }

    override fun setCheckOut(
        prescriptionList: List<PrescriptionVO>,
        deliveryRoutineVO: DeliveryRoutineVO,
        doctorVO: DoctorVO,
        patientVO: PatientVO,
        createdAt: String,
        deliveryAddr: String,
        specialityId: String

    ) {
        mTheCareModelImpl.addCheckOutByPatient(
            deliveryRoutineVO, doctorVO, patientVO, createdAt, deliveryAddr, specialityId
        )
        for (prescription in prescriptionList){
            mTheCareModelImpl.setPrescriptionMedicine(NODE_CHECK_OUT, "Lpibpc0sv4HUg9EhRKbl", prescription)
        }



    }
*/
    /*
    override fun getChatMessage(consultationId: String) {
        mTheCareModelImpl.getChatMessage(consultationId, onSuccess = {
            Log.d("Chat Message", "==> $it")
        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun getCheckOut(owner: LifecycleOwner,patientId: String) {
        mTheCareModelImpl.getCheckOutsFromFirebaseAndSaveToDB(patientId, onFailure = {
            mView?.showError(it)
        })

        mTheCareModelImpl.getCheckOuts()
            .observe(owner, Observer {
                Log.d("CheckOut", "==> $it")
            })

    }

    override fun getConsultations(owner: LifecycleOwner, patientId: String) {
        mTheCareModelImpl.getConsultationsForAPatientFromFirebaseAndSaveToDB(patientId, onFailure = {
            mView?.showError(it)
        })
        mTheCareModelImpl.getConsultations()
    }

    override fun getRecentlyDoctors(patientId: String) {
        mTheCareModelImpl.getRecentlyDoctors(patientId, onSuccess = {
            Log.d("Recent Doctor", "==> $it")
        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun registerPatient(email: String, password: String, username: String, phone: String, image: String) {
        mTheCareModelImpl.setPatient(email, password, username, phone, image)
    }

     */
}