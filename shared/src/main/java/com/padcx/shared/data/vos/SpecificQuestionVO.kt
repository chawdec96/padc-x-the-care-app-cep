package com.padcx.shared.data.vos

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class SpecificQuestionVO(
    var id: String ?= "",
    var sentence: String ?= "",
    var answer: String ?= "",
    var specialityId: String ?= "",
    var type: String ?= ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(sentence)
        parcel.writeString(answer)
        parcel.writeString(specialityId)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SpecificQuestionVO> {
        override fun createFromParcel(parcel: Parcel): SpecificQuestionVO {
            return SpecificQuestionVO(parcel)
        }

        override fun newArray(size: Int): Array<SpecificQuestionVO?> {
            return arrayOfNulls(size)
        }
    }
}