package com.alkema.fityou.domain.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(primaryKeys = ["muscleGroupId", "exerciseId"], foreignKeys = [
    ForeignKey(
        entity = MuscleGroup::class,
        parentColumns = ["muscleGroupId"],
        childColumns = ["muscleGroupId"],
        onDelete = ForeignKey.NO_ACTION
    ),
    ForeignKey(
        entity = Exercise::class,
        parentColumns = ["exerciseId"],
        childColumns = ["exerciseId"],
        onDelete = ForeignKey.CASCADE
    )
])
data class MuscleGroupExerciseCrossRef(
    val muscleGroupId: Long,
    val exerciseId: Long
)
