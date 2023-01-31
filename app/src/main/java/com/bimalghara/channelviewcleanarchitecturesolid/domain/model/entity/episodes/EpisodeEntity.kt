package com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.episodes

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by BimalGhara
 */
@Entity
data class EpisodeEntity(
    @PrimaryKey
    var title: String,

    var channel: String,
    var coverAsset: String

)
