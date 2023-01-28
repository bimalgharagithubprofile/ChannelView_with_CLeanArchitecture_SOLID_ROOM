package com.bimalghara.channelviewcleanarchitecturesolid.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bimalghara.channelviewcleanarchitecturesolid.domain.model.categories.CategoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

@Dao
interface CategoriesDao {

    //can't be suspending because it's Flow
    @Query("SELECT * FROM CategoryEntity")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategories(categories: List<CategoryEntity>): List<Long>

    @Query("DELETE FROM CategoryEntity")
    suspend fun truncate()

}