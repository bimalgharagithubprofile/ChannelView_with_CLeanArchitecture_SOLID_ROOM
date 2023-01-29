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
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    var iconAsset: String? = null,//thumbnail url
    var title: String? = null,
    @TypeConverters(DataConverters::class)
    var channelMedia: List<ChannelMedia> = emptyList(),//all series, if empty then all course
)

data class ChannelMedia(
    var coverAsset: String? = null,//thumbnail url
    var title: String? = null,
    var type: String? = null,//either[image|video]
)




