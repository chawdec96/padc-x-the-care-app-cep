package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.ChatVO

class ChatTypeConverter {

    @TypeConverter
    fun toString(chatList: ArrayList<ChatVO>): String {
        return Gson().toJson(chatList)
    }

    @TypeConverter
    fun toChatList(chatListJsonString: String): ArrayList<ChatVO> {
        val chatListType = object : TypeToken<ArrayList<ChatVO>>() {}.type
        return Gson().fromJson(chatListJsonString, chatListType)
    }
}