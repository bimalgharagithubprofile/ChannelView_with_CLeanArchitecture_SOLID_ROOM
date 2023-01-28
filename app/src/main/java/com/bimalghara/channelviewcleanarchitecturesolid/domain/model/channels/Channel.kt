package com.bimalghara.channelviewcleanarchitecturesolid.domain.model.channels

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by BimalGhara
 */
@Entity
data class ChannelEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    var iconAsset: String? = null,//image url
    var title: String? = null,
    var media: List<Media> = emptyList(),//all series, if empty then all course
)

data class Media(
    val coverAsset: String? = null,//image url
    val title: String? = null,
    val type: String? = null,//either[series|course]
)