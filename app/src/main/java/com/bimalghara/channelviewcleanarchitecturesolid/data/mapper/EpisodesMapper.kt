package com.bimalghara.channelviewcleanarchitecturesolid.data.mapper

import com.bimalghara.channelviewcleanarchitecturesolid.data.model.eposodes.MediaDTO
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.episodes.EpisodeEntity

fun MediaDTO.toDomain(): EpisodeEntity {

    return EpisodeEntity(
        channel = channel.title,
        coverAsset = coverAsset.url,
        title = title
    )
}