package com.padcx.shared.mvp.presenters

import com.padcx.shared.mvp.views.BaseView

interface BasePresenter<T: BaseView> {

    fun initPresenter(view: T)
}