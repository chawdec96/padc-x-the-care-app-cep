
package com.padcx.thecareapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.padcx.shared.activities.BaseActivity
import com.padcx.thecareapp.R
import com.padcx.thecareapp.mvp.presenters.LoginPresenter
import com.padcx.thecareapp.mvp.presenters.impls.LoginPresenterImpl
import com.padcx.thecareapp.mvp.views.LoginView
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
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin("email", "password")
        }

        tv_register.setOnClickListener {
            mPresenter.onTapRegister()
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(LoginPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun navigateToHomeScreen() {
        startActivity(MainActivity.newIntent(this))
    }

    override fun navigateToRegistrationScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}