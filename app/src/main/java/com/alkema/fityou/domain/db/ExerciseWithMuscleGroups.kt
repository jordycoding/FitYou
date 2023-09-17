package com.alkema.fityou.domain.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.alkema.fityou.domain.db.entities.Exercise
import com.alkema.fityou.domain.db.entities.MuscleGroup
import com.alkema.fityou.domain.db.entities.MuscleGroupExerciseCrossRef

data class ExerciseWithMuscleGroups(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "muscleGroupId",
        associateBy = Junction(MuscleGroupExerciseCrossRef::class)
    ) val muscleGroups: List<MuscleGroup>
)
