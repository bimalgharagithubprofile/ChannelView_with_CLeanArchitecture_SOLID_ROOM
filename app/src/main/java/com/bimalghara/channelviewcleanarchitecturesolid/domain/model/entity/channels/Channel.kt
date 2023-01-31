package com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.type_converters.DataConverters

/**
 * Created by BimalGhara
 */
@Entity
data class ChannelEntity(
    @PrimaryKey
    var title: String,

    var iconAsset: String,//thumbnail url

    var channelMedia: List<ChannelMedia> = emptyList(),//all series, if empty then all course
)

data class ChannelMedia(
    var coverAsset: String,//thumbnail url
    var title: String,
    var type: String,//either[image|video]
)




