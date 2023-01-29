package com.bimalghara.channelviewcleanarchitecturesolid.domain.model.type_converters

import androidx.room.TypeConverter
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelMedia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverters {

    @TypeConverter
    fun convertChannelMediaToString(value: List<ChannelMedia>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ChannelMedia>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun convertStringToChannelMedia(value: String): List<ChannelMedia> {
        val gson = Gson()
        val type = object : TypeToken<List<ChannelMedia>>() {}.type
        return gson.fromJson(value, type)
    }
}