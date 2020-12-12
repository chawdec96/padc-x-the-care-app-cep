package com.padcx.shared

interface BasePresenter<T: BaseView> {

    fun initPresenter(view: T)
}