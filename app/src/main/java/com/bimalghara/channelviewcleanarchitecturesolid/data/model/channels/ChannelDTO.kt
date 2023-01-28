package com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels

data class ChannelDTO(
    val coverAsset: CoverAssetDTO,
    val iconAsset: IconAssetDTO,
    val id: String,
    val latestMedia: List<LatestMedia>,
    val mediaCount: Int,
    val series: List<SeryDTO>,
    val slug: String,
    val title: String
)