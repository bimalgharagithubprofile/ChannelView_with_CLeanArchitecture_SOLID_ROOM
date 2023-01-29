package com.bimalghara.channelviewcleanarchitecturesolid.data.model.channels

data class LatestMedia(
    val coverAsset: CoverAssetDTO,
    val title: String? = null,
    val type: String? = null
)