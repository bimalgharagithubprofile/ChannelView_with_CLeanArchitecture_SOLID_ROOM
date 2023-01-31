package com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.categories

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by BimalGhara
 */
@Entity
data class CategoryEntity(
    @PrimaryKey
    var name: String

)
