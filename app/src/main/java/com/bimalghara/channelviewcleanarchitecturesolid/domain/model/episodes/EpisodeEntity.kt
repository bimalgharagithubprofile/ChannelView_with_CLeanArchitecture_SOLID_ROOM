package com.bimalghara.channelviewcleanarchitecturesolid.domain.model.episodes

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by BimalGhara
 */
@Entity
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    var channel: String? = null,
    var coverAsset: String? = null,
    var title: String? = null

)
