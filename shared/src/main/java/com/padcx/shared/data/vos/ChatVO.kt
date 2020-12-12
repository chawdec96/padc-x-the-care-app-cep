package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class ChatVO (
    var id : String = " ",
    var date: String ?= "",
    var image: String ?= "",
    var text: String ?= "",
    var sendTime: String ?= "",
    var sender: Any ?= ""
)