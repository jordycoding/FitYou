package com.alkema.fityou.domain.db

import androidx.room.Embedded
import androidx.room.Relation
import com.alkema.fityou.domain.db.entities.Workout
import com.alkema.fityou.domain.db.entities.WorkoutEntry

data class WorkoutWithEntries(
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "workoutId"
    ) val entries: List<WorkoutEntry>
)
