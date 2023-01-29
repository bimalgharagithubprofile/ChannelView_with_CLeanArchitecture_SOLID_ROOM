package com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels

data class ChannelDTO(
    val coverAsset: CoverAssetDTO? = null,
    val iconAsset: IconAssetDTO? = null,
    val id: String? = null,
    val latestMedia: List<LatestMedia>? = null,
    val mediaCount: Int? = null,
    val series: List<SeryDTO>? = null,
    val slug: String? = null,
    val title: String? = null
)