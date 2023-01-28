package com.bimalghara.channelviewcleanarchitecturesolid.data.model.eposodes

data class MediaDTO(
    val channel: ChannelDTO,
    val coverAsset: CoverAssetDTO,
    val title: String,
    val type: String
)