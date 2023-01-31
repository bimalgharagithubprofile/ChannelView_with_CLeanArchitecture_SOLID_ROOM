package com.bimalghara.channelviewcleanarchitecturesolid.data.mapper

import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.ChannelDTO
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.LatestMedia
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.SeryDTO
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelMedia

fun ChannelDTO.toDomain() : ChannelEntity {

    val media = if(!series.isNullOrEmpty())
        series.map { it.toDomain() }
    else
        latestMedia?.map { it.toDomain() } ?: emptyList()

    return ChannelEntity(
        title = title ?: "",
        iconAsset = iconAsset?.url ?: "",
        channelMedia = media
    )
}

private fun SeryDTO.toDomain() : ChannelMedia {
    return ChannelMedia(
        coverAsset = coverAsset?.url ?: "",
        title = title ?: "",
        type = "image"
    )
}

private fun LatestMedia.toDomain() : ChannelMedia {
    return ChannelMedia(
        coverAsset = coverAsset.url ?: "",
        title = title ?: "",
        type = type ?: ""
    )
}