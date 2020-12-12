package com.padcx.doctors.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.padcx.doctors.R
import com.padcx.doctors.mvp.presenters.RegisterPresenter
import com.padcx.doctors.mvp.presenters.impls.RegisterPresenterImpl
import com.padcx.doctors.mvp.views.RegisterView
import com.padcx.shared.activities.BaseActivity

class RegisterActivity : BaseActivity(), RegisterView {

    private lateinit var mPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setUpPresenter()
        setUpListener()

    }

    private fun setUpListener() {
        //TODO btn register
        mPresenter.onTapRegister(
            "name", "email", "password","phone",
            "speciality", "university","degree",
            "doctorSignature", "image"
        )

        //TODO text login
        mPresenter.onTapLogin()
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(RegisterPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun navigateToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
    }
}