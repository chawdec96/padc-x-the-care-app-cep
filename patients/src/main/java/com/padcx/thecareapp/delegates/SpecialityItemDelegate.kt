package com.padcx.thecareapp.delegates

import com.padcx.shared.data.vos.SpecialityVO

interface SpecialityItemDelegate {

    fun onTapItem(specialityVO: SpecialityVO)
}