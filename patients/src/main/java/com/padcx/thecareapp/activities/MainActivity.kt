package com.padcx.thecareapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vos.*
import com.padcx.thecareapp.R
import com.padcx.thecareapp.fragments.ConsultationsFragment
import com.padcx.thecareapp.fragments.HomeFragment
import com.padcx.thecareapp.fragments.ProfileFragment
import com.padcx.thecareapp.mvp.presenters.MainPresenter
import com.padcx.thecareapp.mvp.presenters.impls.MainPresenterImpl
import com.padcx.thecareapp.mvp.views.MainView
import com.padcx.thecareapp.persistence.PatientPrefs
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), MainView {

    private lateinit var mPresenter: MainPresenter
    private lateinit var patientVO: PatientVO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.toolbar))

        setUpPresenter()

        patientVO = PatientPrefs.getUserInfo()
        mPresenter.onUIReady(this, patientVO.id)

//        mPresenter.getRecentlyDoctors(this, patientVO.id)
//        mPresenter.getGeneralQuestions(this, patientVO.id)

        loadFragment(HomeFragment.newInstance())
        setUpBottomNavigation()

    }

    private fun setUpBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment.newInstance())
                }
                R.id.navigation_sms -> {
                    loadFragment(ConsultationsFragment())
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                }
                else -> {
                    loadFragment(HomeFragment.newInstance())
                }
            }
            false
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(MainPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {

        const val TAG = "MainActivity"

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun addGeneralQuestionsToPatientVO(questions: List<GeneralQuestionVO>) {
        patientVO.generalQuestions?.addAll(questions)
    }

    override fun addRecentlyDoctorsToPatientVO(doctors: List<DoctorVO>) {
        patientVO.recentDoctor?.addAll(doctors)
    }
}