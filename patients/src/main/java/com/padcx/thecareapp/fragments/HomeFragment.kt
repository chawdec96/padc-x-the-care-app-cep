package com.padcx.thecareapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.padcx.shared.data.vos.ConsultationVO
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.thecareapp.R
import com.padcx.thecareapp.adapter.SpecialityRecyclerAdapter
import com.padcx.thecareapp.mvp.presenters.HomePresenter
import com.padcx.thecareapp.mvp.presenters.impls.HomePresenterImpl
import com.padcx.thecareapp.mvp.views.HomeView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeView {

    private lateinit var mPresenter: HomePresenter
    private lateinit var mSpecialityAdapter: SpecialityRecyclerAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecycler()

        mPresenter.onUIReady(this)
        mPresenter.getRecentDoctors(this)

    }

    private fun setUpRecycler() {
        mSpecialityAdapter = SpecialityRecyclerAdapter(mPresenter)

        rvSpecialities.layoutManager = GridLayoutManager(this.requireContext(), 2)
        rvSpecialities.adapter = mSpecialityAdapter
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    companion object{
        fun newInstance(): Fragment{
            return HomeFragment()
        }
    }

    override fun showSpecialities(specialities: List<SpecialityVO>) {
        specialities.let {
            mSpecialityAdapter.setNewData(specialities)
        }
    }

    override fun showRecentDoctors(doctors: List<DoctorVO>) {

        if (doctors.isNotEmpty()){
            tvRecentDoctors.visibility = View.VISIBLE
            rvRecentDoctors.visibility = View.VISIBLE
        }else{
            tvRecentDoctors.visibility = View.GONE
            rvRecentDoctors.visibility = View.GONE
        }
    }

    override fun showConsultations(consultations: List<ConsultationVO>) {
        if (consultations.isNotEmpty()){
            rvChats.visibility = View.VISIBLE
        }else{
            rvChats.visibility = View.GONE
        }
    }

    override fun showConfirmConsultationDialog(specialityVO: SpecialityVO) {
        ConfirmConsultationDialogFragment.newInstance(specialityVO)
            .show(childFragmentManager, "Dialog")
    }

    override fun showError(error: String) {
        TODO("Not yet implemented")
    }

}