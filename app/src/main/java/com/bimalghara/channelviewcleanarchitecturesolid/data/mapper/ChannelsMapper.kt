package com.bimalghara.channelviewcleanarchitecturesolid.data.mapper

import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.ChannelDTO
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.LatestMedia
import com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels.SeryDTO
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.channels.ChannelMedia

fun ChannelDTO.toDomain() : ChannelEntity {
    val channelEntity = ChannelEntity()

    channelEntity.iconAsset = iconAsset?.url
    channelEntity.title = title
    if(series?.size == 0)
        channelEntity.channelMedia = series.map { it.toDomain() }
    else
        channelEntity.channelMedia = latestMedia?.map { it.toDomain() } ?: emptyList()

    return channelEntity
}

private fun SeryDTO.toDomain() : ChannelMedia {
    return ChannelMedia(
        coverAsset = coverAsset.url,
        title = title,
        type = "image"
    )
}

private fun LatestMedia.toDomain() : ChannelMedia {
    return ChannelMedia(
        coverAsset = coverAsset.url,
        title = title,
        type = type
    )
}