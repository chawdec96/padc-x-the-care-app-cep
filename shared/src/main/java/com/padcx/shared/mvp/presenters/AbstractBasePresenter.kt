package com.padcx.shared.mvp.presenters

import androidx.lifecycle.ViewModel
import com.padcx.shared.mvp.views.BaseView

abstract class AbstractBasePresenter<T: BaseView>: BasePresenter<T>, ViewModel() {

    var mView: T ?= null

    override fun initPresenter(view: T) {
        mView = view
    }

}