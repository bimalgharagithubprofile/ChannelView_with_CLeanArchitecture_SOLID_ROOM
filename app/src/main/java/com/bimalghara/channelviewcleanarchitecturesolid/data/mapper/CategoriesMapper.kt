package com.bimalghara.channelviewcleanarchitecturesolid.data.mapper

import com.bimalghara.channelviewcleanarchitecturesolid.data.model.categories.CategoryDTO
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.categories.CategoryEntity

fun CategoryDTO.toDomain() : CategoryEntity {
    return CategoryEntity(
        name = name
    )
}