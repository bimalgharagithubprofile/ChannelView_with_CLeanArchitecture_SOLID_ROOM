package com.bimalghara.channelviewcleanarchitecturesolid.domain.model.episodes

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by BimalGhara
 */
@Entity
data class Episodes(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String = "",
    val coverAsset: String = "",
    val channel: String = ""

)
