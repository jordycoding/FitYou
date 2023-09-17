package com.alkema.fityou.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alkema.fityou.domain.db.entities.Exercise
import com.alkema.fityou.domain.db.entities.MuscleGroup
import com.alkema.fityou.domain.db.entities.MuscleGroupExerciseCrossRef
import com.alkema.fityou.domain.db.entities.Routine
import com.alkema.fityou.domain.db.entities.Subroutine

@Database(
    entities = [Exercise::class, MuscleGroup::class, MuscleGroupExerciseCrossRef::class, Routine::class, Subroutine::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}