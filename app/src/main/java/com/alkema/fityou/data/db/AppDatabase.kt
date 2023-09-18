package com.alkema.fityou.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alkema.fityou.domain.db.entities.Exercise
import com.alkema.fityou.domain.db.entities.MuscleGroup
import com.alkema.fityou.domain.db.entities.MuscleGroupExerciseCrossRef
import com.alkema.fityou.domain.db.entities.Routine
import com.alkema.fityou.domain.db.entities.Subroutine
import com.alkema.fityou.domain.db.entities.Workout
import com.alkema.fityou.domain.db.entities.WorkoutComment
import com.alkema.fityou.domain.db.entities.WorkoutEntry
import com.alkema.fityou.domain.db.entities.WorkoutEntryComment

@Database(
    entities = [Exercise::class, MuscleGroup::class, MuscleGroupExerciseCrossRef::class, Routine::class, Subroutine::class, Workout::class, WorkoutComment::class, WorkoutEntry::class, WorkoutEntryComment::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun muscleGroupDao(): MuscleGroupDao
    abstract fun exerciseMuscleGroupDao(): ExerciseMuscleGroupDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutEntryDao(): WorkoutEntryDao
}