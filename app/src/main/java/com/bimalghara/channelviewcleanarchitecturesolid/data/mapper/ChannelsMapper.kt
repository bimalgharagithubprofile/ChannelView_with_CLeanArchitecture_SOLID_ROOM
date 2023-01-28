package com.bimalghara.channelviewcleanarchitecturesolid.data.mapper

import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.ChannelDTO
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.LatestMedia
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.SeryDTO
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.channels.Media

fun ChannelDTO.toDomain() : ChannelEntity {
    val channelEntity = ChannelEntity()

    channelEntity.iconAsset = iconAsset.url
    channelEntity.title = title
    if(series.isNotEmpty())
        channelEntity.media = series.map { it.toDomain() }
    else
        channelEntity.media = latestMedia.map { it.toDomain() }

    return channelEntity
}

private fun SeryDTO.toDomain() : Media {
    return Media(
        title = title,
        type = "image",
        coverAsset = coverAsset.url
    )
}

private fun LatestMedia.toDomain() : Media {
    return Media(
        title = title,
        type = type,
        coverAsset = coverAsset.url
    )
}