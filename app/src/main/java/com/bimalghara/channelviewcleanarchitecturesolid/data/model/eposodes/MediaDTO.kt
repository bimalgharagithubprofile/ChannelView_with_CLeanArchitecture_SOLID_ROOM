package com.bimalghara.channelviewcleanarchitecturesolid.data.model.eposodes

data class MediaDTO(
    val channel: ChannelDTO? = null,
    val coverAsset: CoverAssetDTO? = null,
    val title: String? = null,
    val type: String? = null
)