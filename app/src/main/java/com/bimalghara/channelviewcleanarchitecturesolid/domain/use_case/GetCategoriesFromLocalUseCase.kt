package com.bimalghara.channelviewcleanarchitecturesolid.domain.use_case

import com.bimalghara.channelviewcleanarchitecturesolid.common.dispatcher.DispatcherProviderSource
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.entity.categories.CategoryEntity
import com.bimalghara.channelviewcleanarchitecturesolid.domain.repository.CategoriesRepositorySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

/**
 * Created by BimalGhara
 */

class GetCategoriesFromLocalUseCase(
    private val dispatcherProviderSource: DispatcherProviderSource,
    private val categoriesRepositorySource: CategoriesRepositorySource
) {

    operator fun invoke(): Flow<List<CategoryEntity>> {
        return categoriesRepositorySource.getCategoryListFromLocal().flowOn(dispatcherProviderSource.io)

    }

}