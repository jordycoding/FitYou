package com.alkema.fityou.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alkema.fityou.domain.db.entities.MuscleGroup

@Dao
interface MuscleGroupDao {
    @Query("SELECT * FROM MuscleGroup")
    fun getAll(): List<MuscleGroup>

    @Insert
    fun insertAll(vararg muscleGroups: MuscleGroup)

    @Delete
    fun delete(muscleGroup: MuscleGroup)
}