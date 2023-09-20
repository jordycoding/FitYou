package com.alkema.fityou.domain.db

import androidx.room.Embedded
import androidx.room.Relation
import com.alkema.fityou.domain.db.entities.Workout
import com.alkema.fityou.domain.db.entities.WorkoutEntry

data class WorkoutWithEntriesAndExercises(
    @Embedded val workout: Workout,
    @Relation(
        entity = WorkoutEntry::class,
        parentColumn = "workoutId",
        entityColumn = "workoutId"
    ) val entries: List<WorkoutEntryWithExercise>
)
