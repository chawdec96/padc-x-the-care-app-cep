package com.padcx.doctors.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.padcx.doctors.R
import com.padcx.doctors.mvp.presenters.LoginPresenter
import com.padcx.doctors.mvp.presenters.impls.LoginPresenterImpl
import com.padcx.doctors.mvp.views.LoginView
import com.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {

    private lateinit var mPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpPresenter()
        setUpListener()
    }

    private fun setUpListener() {
        btnFacebookSignIn.setOnClickListener {
            mPresenter.onTapRegister()
        }

        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(etEmail.text.toString(), etPassword.text.toString())
        }

    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(LoginPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun navigateToHomeScreen() {
        startActivity(MainActivity.newIntent(this))
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }
}