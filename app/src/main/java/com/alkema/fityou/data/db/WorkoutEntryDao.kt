package com.alkema.fityou.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alkema.fityou.domain.db.entities.WorkoutEntry

@Dao
interface WorkoutEntryDao {
    @Query("SELECT * FROM WorkoutEntry")
    fun getAll(): List<WorkoutEntry>

    @Insert
    fun insertAll(vararg workoutEntries: WorkoutEntry)
}