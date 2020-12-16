package com.padcx.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.padcx.shared.activities.BaseActivity
import com.padcx.thecareapp.R
import com.padcx.thecareapp.mvp.presenters.RegisterPresenter
import com.padcx.thecareapp.mvp.presenters.impls.RegisterPresenterImpl
import com.padcx.thecareapp.mvp.views.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterView {

    private lateinit var mPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setUpPresenter()
        setUpListener()

    }

    private fun setUpListener() {
        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()
            val username = etName.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && phone.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()){
                mPresenter.onTapRegister(email, username, password, phone, "", "", "","",
                "", "", "")
            }
        }
        
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